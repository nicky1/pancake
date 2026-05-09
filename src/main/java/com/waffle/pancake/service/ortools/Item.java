package com.waffle.pancake.service.ortools;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2025/6/17
 **/
public class Item {
	final int id;
	final int width;
	final int height;
	final int depth;

	public Item(int id, int width, int height, int depth) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public int volume() {
		return width * height * depth;
	}

	@Override
	public String toString() {
		return String.format("Item%d(%dx%dx%d)", id, width, height, depth);
	}
}
