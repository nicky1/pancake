package com.waffle.pancake.service.ortools;

import com.google.ortools.sat.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2025/6/17
 **/
@Slf4j
public class BinPackingSolver {
	private Bin bin;
	private List<Item> items;
	private boolean allowRotations;
	private int maxBins;

	private CpModel model;
	private CpSolver solver;
	private List<IntVar> binAssignment;
	private List<IntVar> xPos;
	private List<IntVar> yPos;
	private List<IntVar> zPos;
	private List<IntVar> rotationChoices;
	private List<List<int[]>> itemRotations;
	private LinearArgument[] binUsed = new LinearArgument[]{};

	private List<PlacedItem> solution;
	private int numBinsUsed;
	private double solveTime;
	private boolean solutionFound;

	public BinPackingSolver(Bin bin, List<Item> items, boolean allowRotations, int maxBins) {
		this.bin = bin;
		this.items = items;
		this.allowRotations = allowRotations;
		this.maxBins = maxBins;
	}

	// 生成物品的所有可能旋转
	private List<int[]> generateRotations(Item item) {
		Set<String> seen = new HashSet<>();
		List<int[]> rotations = new ArrayList<>();

		int[][] dimPermutations = {
			{item.width, item.height, item.depth},
			{item.width, item.depth, item.height},
			{item.height, item.width, item.depth},
			{item.height, item.depth, item.width},
			{item.depth, item.width, item.height},
			{item.depth, item.height, item.width}
		};

		for (int[] dims : dimPermutations) {
			String key = dims[0] + "," + dims[1] + "," + dims[2];
			if (!seen.contains(key)) {
				seen.add(key);
				rotations.add(dims);
			}
		}

		return rotations;
	}

	// 构建3D装箱模型
	public void buildModel() {
		model = new CpModel();
		int numItems = items.size();

		// 1. 决策变量初始化
		binAssignment = new ArrayList<>();
		xPos = new ArrayList<>();
		yPos = new ArrayList<>();
		zPos = new ArrayList<>();
		rotationChoices = new ArrayList<>();
		itemRotations = new ArrayList<>();
		binUsed = new IntVar[]{};

		// 箱子分配变量 (每个物品分配的箱子索引)
		for (int i = 0; i < numItems; i++) {
			binAssignment.add(model.newIntVar(0, maxBins - 1, "bin_" + i));
		}

		// 位置变量
		for (int i = 0; i < numItems; i++) {
			xPos.add(model.newIntVar(0, bin.width, "x_" + i));
			yPos.add(model.newIntVar(0, bin.height, "y_" + i));
			zPos.add(model.newIntVar(0, bin.depth, "z_" + i));
		}

		// 旋转变量和旋转配置
		for (int i = 0; i < numItems; i++) {
			List<int[]> rotations;
			if (allowRotations) {
				rotations = generateRotations(items.get(i));
			} else {
				rotations = Collections.singletonList(
					new int[]{items.get(i).width, items.get(i).height, items.get(i).depth});
			}

			itemRotations.add(rotations);
			rotationChoices.add(model.newIntVar(0, rotations.size() - 1, "rot_" + i));
		}
		// 箱子使用变量
		for (int b = 0; b < maxBins; b++) {
			binUsed[b] =model.newBoolVar("bin_used_" + b);
		}

		// 2. 约束: 箱子使用与物品分配关系
		for (int b = 0; b < maxBins; b++) {
			LinearArgument[] binItems = new LinearArgument[numItems];
			for (int i = 0; i < numItems; i++) {
				Literal inBin = model.newBoolVar("item_" + i + "_in_bin_" + b);
				model.addEquality(binAssignment.get(i), b).onlyEnforceIf(inBin);
				model.addDifferent(binAssignment.get(i), b).onlyEnforceIf(inBin.not());
				binItems[0] =inBin;
			}
			// 如果有物品在箱子中，则标记箱子已使用
			model.addGreaterThan(LinearExpr.sum(binItems), 0).onlyEnforceIf((Literal) binUsed[b]);
			model.addEquality(LinearExpr.sum(binItems), 0).onlyEnforceIf(((Literal) binUsed[b]).not());
		}
		// 3. 约束: 物品必须在箱子边界内
		for (int i = 0; i < numItems; i++) {
			List<int[]> rotations = itemRotations.get(i);
			IntVar rotVar = rotationChoices.get(i);

			// 为每个可能的旋转方向添加约束
			for (int r = 0; r < rotations.size(); r++) {
				int[] dims = rotations.get(r);
				int w = dims[0], h = dims[1], d = dims[2];

				// 当选择此旋转时，确保物品在箱子内
				Literal isRotation = model.newBoolVar("rot_" + i + "_" + r);
				model.addEquality(rotVar, r).onlyEnforceIf(isRotation);
				model.addDifferent(rotVar, r).onlyEnforceIf(isRotation.not());

				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{xPos.get(i), model.newConstant(w)}), bin.width)
					.onlyEnforceIf(isRotation);
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{yPos.get(i), model.newConstant(h)}), bin.height)
					.onlyEnforceIf(isRotation);
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{zPos.get(i), model.newConstant(d)}), bin.depth)
					.onlyEnforceIf(isRotation);
			}
		}
		// 4. 约束: 物品之间不能重叠 (同一个箱子内)
		for (int i = 0; i < numItems; i++) {
			for (int j = i + 1; j < numItems; j++) {
				// 检查两个物品是否在同一个箱子中
				Literal sameBin = model.newBoolVar("same_bin_" + i + "_" + j);
				model.addEquality(binAssignment.get(i), binAssignment.get(j)).onlyEnforceIf(sameBin);
				model.addDifferent(binAssignment.get(i), binAssignment.get(j)).onlyEnforceIf(sameBin.not());

				// 为两个物品定义尺寸变量
				IntVar iw = model.newIntVar(0, bin.width, "iw_" + i + "_" + j);
				IntVar ih = model.newIntVar(0, bin.height, "ih_" + i + "_" + j);
				IntVar id = model.newIntVar(0, bin.depth, "id_" + i + "_" + j);

				IntVar jw = model.newIntVar(0, bin.width, "jw_" + i + "_" + j);
				IntVar jh = model.newIntVar(0, bin.height, "jh_" + i + "_" + j);
				IntVar jd = model.newIntVar(0, bin.depth, "jd_" + i + "_" + j);

				// 将尺寸变量与旋转选择关联
				for (int r = 0; r < itemRotations.get(i).size(); r++) {
					int[] dims = itemRotations.get(i).get(r);
					Literal rotI = model.newBoolVar("rot_i_" + i + "_" + j + "_" + r);
					model.addEquality(rotationChoices.get(i), r).onlyEnforceIf(rotI);
					model.addDifferent(rotationChoices.get(i), r).onlyEnforceIf(rotI.not());

					model.addEquality(iw, dims[0]).onlyEnforceIf(rotI);
					model.addEquality(ih, dims[1]).onlyEnforceIf(rotI);
					model.addEquality(id, dims[2]).onlyEnforceIf(rotI);
				}
				for (int r = 0; r < itemRotations.get(j).size(); r++) {
					int[] dims = itemRotations.get(j).get(r);
					Literal rotJ = model.newBoolVar("rot_j_" + i + "_" + j + "_" + r);
					model.addEquality(rotationChoices.get(j), r).onlyEnforceIf(rotJ);
					model.addDifferent(rotationChoices.get(j), r).onlyEnforceIf(rotJ.not());

					model.addEquality(jw, dims[0]).onlyEnforceIf(rotJ);
					model.addEquality(jh, dims[1]).onlyEnforceIf(rotJ);
					model.addEquality(jd, dims[2]).onlyEnforceIf(rotJ);
				}

				// 非重叠约束: 至少在一个方向上分离
				Literal left = model.newBoolVar("left_" + i + "_" + j);
				Literal right = model.newBoolVar("right_" + i + "_" + j);
				Literal below = model.newBoolVar("below_" + i + "_" + j);
				Literal above = model.newBoolVar("above_" + i + "_" + j);
				Literal behind = model.newBoolVar("behind_" + i + "_" + j);
				Literal front = model.newBoolVar("front_" + i + "_" + j);

				// X方向: i在j的左边或右边
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{xPos.get(i), iw}), xPos.get(j))
					.onlyEnforceIf(left);
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{xPos.get(j), jw}), xPos.get(i))
					.onlyEnforceIf(right);

				// Y方向: i在j的下面或上面
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{yPos.get(i), ih}), yPos.get(j))
					.onlyEnforceIf(below);
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{yPos.get(j), jh}), yPos.get(i))
					.onlyEnforceIf(above);

				// Z方向: i在j的后面或前面
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{zPos.get(i), id}), zPos.get(j))
					.onlyEnforceIf(behind);
				model.addLessOrEqual(LinearExpr.sum(new IntVar[]{zPos.get(j), jd}), zPos.get(i))
					.onlyEnforceIf(front);
				// 当在同一个箱子时，必须至少有一个分离条件成立
				Literal[] separations = {left, right, below, above, behind, front};
				model.addBoolOr(separations).onlyEnforceIf(sameBin);
			}
		}

		// 5. 目标: 最小化使用的箱子数量
		model.minimize(LinearExpr.sum(binUsed));
	}

	// 求解3D装箱问题
	public CpSolverStatus solve() {
		solver = new CpSolver();
		solver.getParameters().setMaxTimeInSeconds(300); // 5分钟超时

		long startTime = System.currentTimeMillis();
		CpSolverStatus status = solver.solve(model);
		solveTime = (System.currentTimeMillis() - startTime) / 1000.0;

		if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
			solutionFound = true;
			extractSolution();
		}

		return status;
	}

	// 从求解器中提取解决方案
	private void extractSolution() {
		solution = new ArrayList<>();
		numBinsUsed = 0;

		// 确定使用的箱子
		boolean[] usedBins = new boolean[maxBins];
		for (int b = 0; b < maxBins; b++) {
			if (solver.booleanValue((Literal) binUsed[b])) {
				usedBins[b] = true;
				numBinsUsed++;
			}
		}

		// 收集每个物品的放置信息
		for (int i = 0; i < items.size(); i++) {
			int binIdx = (int) solver.value(binAssignment.get(i));
			int x = (int) solver.value(xPos.get(i));
			int y = (int) solver.value(yPos.get(i));
			int z = (int) solver.value(zPos.get(i));
			int rot = (int) solver.value(rotationChoices.get(i));
			int[] dims = itemRotations.get(i).get(rot);

			solution.add(new PlacedItem(items.get(i), binIdx, x, y, z, rot, dims));
		}
	}

	// 获取解决方案
	public List<PlacedItem> getSolution() {
		return solution;
	}

	public int getNumBinsUsed() {
		return numBinsUsed;
	}

	public double getSolveTime() {
		return solveTime;
	}

	public boolean isSolutionFound() {
		return solutionFound;
	}

	// 打印解决方案摘要
	public void printSolutionSummary() {
		if (!solutionFound) {
			System.out.println("未找到可行解");
			return;
		}

		System.out.println("\n=== 3D装箱解决方案 ===");
		System.out.printf("使用的箱子数量: %d\n"+ numBinsUsed);
		System.out.printf("求解时间: %.2f秒\n"+solveTime);
		System.out.printf("物品总数: %d\n"+ items.size());

		// 按箱子分组
		Map<Integer, List<PlacedItem>> binMap = solution.stream()
			.collect(Collectors.groupingBy(item -> (Integer) item.binIndex));

		for (Map.Entry<Integer, List<PlacedItem>> entry : binMap.entrySet()) {
			for (PlacedItem item : entry.getValue()) {
				System.out.printf("  %s\n", item);
			}
		}
	}

	// 打印空间利用率
	public void printSpaceUtilization() {
		if (!solutionFound) return;

		// 按箱子分组
		Map<Integer, List<PlacedItem>> binMap = solution.stream()
			.collect(Collectors.groupingBy(item -> (Integer) item.binIndex));

		System.out.println("\n空间利用率分析:");
		for (Map.Entry<Integer, List<PlacedItem>> entry : binMap.entrySet()) {
			int binVol = bin.volume();
			int usedVol = entry.getValue().stream()
				.mapToInt(item -> item.dimensions[0] * item.dimensions[1] * item.dimensions[2])
				.sum();

			double utilization = 100.0 * usedVol / binVol;
//			System.out.printf("箱子 %d: 使用体积 %d/%d (%.1f%%)\n",
//				entry.getKey() + 1, usedVol, binVol, utilization);
		}
	}

	// 可视化箱子内容 (简单文本可视化)
	public void visualizeBin(int binIndex) {
		if (!solutionFound) return;

		List<PlacedItem> binItems = solution.stream()
			.filter(item -> item.binIndex == binIndex)
			.collect(Collectors.toList());

		if (binItems.isEmpty()) {
//			System.out.printf("箱子 %d 是空的\n", binIndex + 1);
			return;
		}

//		System.out.printf("\n箱子 %d 可视化 (XY平面切片):\n", binIndex + 1);

		// 创建网格表示
		char[][][] grid = new char[bin.depth][bin.height][bin.width];
		for (char[][] layer : grid) {
			for (char[] row : layer) {
				Arrays.fill(row, '.');
			}
		}
		// 标记物品位置
		for (PlacedItem item : binItems) {
			int x = item.x;
			int y = item.y;
			int z = item.z;
			int w = item.dimensions[0];
			int h = item.dimensions[1];
			int d = item.dimensions[2];

			// 为每个物品使用唯一字符
			char symbol = (char) ('A' + item.item.id % 26);

			for (int zi = z; zi < z + d && zi < bin.depth; zi++) {
				for (int yi = y; yi < y + h && yi < bin.height; yi++) {
					for (int xi = x; xi < x + w && xi < bin.width; xi++) {
						grid[zi][yi][xi] = symbol;
					}
				}
			}
		}

		// 打印各层
		for (int z = 0; z < bin.depth; z++) {
			for (int y = 0; y < bin.height; y++) {
				for (int x = 0; x < bin.width; x++) {
					System.out.print(grid[z][y][x] + " ");
				}
				System.out.println();
			}
		}
	}

	// 生成随机物品
	private static List<Item> generateRandomItems(int numItems, int minSize, int maxSize) {
		List<Item> items = new ArrayList<>();
		Random rand = new Random();

		for (int i = 0; i < numItems; i++) {
			int w = minSize + rand.nextInt(maxSize - minSize + 1);
			int h = minSize + rand.nextInt(maxSize - minSize + 1);
			int d = minSize + rand.nextInt(maxSize - minSize + 1);
			items.add(new Item(i + 1, w, h, d));
		}

		return items;
	}

	static {
		// 设置本地库路径（根据你的操作系统）
		String os = System.getProperty("os.name").toLowerCase();
		String libPath = "";

		if (os.contains("win")) {
			libPath = "path/to/lib/jniortools.dll";
		} else if (os.contains("mac")) {
			libPath = "/Users/yixiaoshuang/workspace/deep/libjniortools.dylib";
		} else if (os.contains("nix") || os.contains("nux")) {
			libPath = "path/to/lib/libjniortools.so";
		}

		// 确保在 macOS 上正确加载 OR-Tools 库
//		try {
//			// 使用统一的正斜杠路径分隔符
//			String libDir = System.getProperty("user.dir") + "/src/main/resources/ortools-darwin";
//			System.setProperty("java.library.path", System.getProperty("java.library.path") +
//					":" + libDir.replace("\\", "/"));
//
//			// 显式加载 OR-Tools 本地库
//			System.load(libDir.replace("\\", "/") + "libjniortools.dylib");
//			System.out.println("成功加载 jniortools 库");
//		} catch (UnsatisfiedLinkError e) {
//			System.err.println("无法加载 jniortools 库: " + e.getMessage());
//			System.err.println("java.library.path: " + System.getProperty("java.library.path"));
//
//			// 尝试使用 OR-Tools 的 Loader 作为备选
//			try {
//				Loader.loadNativeLibraries();
//				System.out.println("使用 OR-Tools Loader 加载成功");
//			} catch (Exception ex) {
//				System.err.println("Loader 加载失败: " + ex.getMessage());
//			}
//		}


//		System.load(libPath);
	}
	// 主程序
	public static void main(String[] args) {
		// 加载OR-Tools本地库
//		Loader.loadNativeLibraries();
		// 加载OR-Tools本地库
//		System.loadLibrary("jniortools");

		// 定义箱子尺寸
		Bin bin = new Bin(10, 10, 10);

		// 生成随机物品
		List<Item> items = generateRandomItems(15, 1, 5);

		System.out.println("箱子尺寸: " + bin);
		System.out.println("物品列表:");
		items.forEach(item -> System.out.println("  " + item));

		// 创建求解器
		BinPackingSolver solver = new BinPackingSolver(bin, items, true, 1);

		// 构建并求解模型
		System.out.println("\n开始求解3D装箱问题...");
		solver.buildModel();
		CpSolverStatus status = solver.solve();

		// 输出结果
		if (status == CpSolverStatus.OPTIMAL) {
			System.out.println("\n找到最优解!");
		} else if (status == CpSolverStatus.FEASIBLE) {
			System.out.println("\n找到可行解（可能不是最优）");
		} else {
			System.out.println("\n未找到可行解");
		}

		if (solver.isSolutionFound()) {
			solver.printSolutionSummary();
			solver.printSpaceUtilization();
			solver.visualizeBin(0); // 可视化第一个箱子
		}
	}
}
