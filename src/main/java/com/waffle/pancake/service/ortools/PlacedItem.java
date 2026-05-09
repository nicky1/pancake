package com.waffle.pancake.service.ortools;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2025/6/17
 **/
public class PlacedItem {
	final Item item;
	final int binIndex;
	final int x;
	final int y;
	final int z;
	final int rotation;
	final int[] dimensions;

	public PlacedItem(Item item, int binIndex, int x, int y, int z, int rotation, int[] dimensions) {
		this.item = item;
		this.binIndex = binIndex;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotation = rotation;
		this.dimensions = dimensions;
	}

	@Override
	public String toString() {
		return String.format("%s at (%d,%d,%d) rot:%d dim:%dx%dx%d",
			item, x, y, z, rotation, dimensions[0], dimensions[1], dimensions[2]);
	}
}
