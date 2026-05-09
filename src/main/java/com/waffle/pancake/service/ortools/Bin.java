package com.waffle.pancake.service.ortools;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2025/6/17
 **/
public class Bin {
	final int width;
	final int height;
	final int depth;

	public Bin(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public int volume() {
		return width * height * depth;
	}

	@Override
	public String toString() {
		return String.format("Bin(%dx%dx%d)", width, height, depth);
	}
}
