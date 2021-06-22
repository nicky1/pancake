package com.waffae.pancake.integrated.tech.memory;

import java.io.IOException;

/**
 * @author yixiaoshuang
 * @date 2020/12/30 22:18
 */
public class NormalReference {

    public static void main(String[] args) throws IOException {
        // 这里重写finalize方法，便于观察GC情况，日程开发中，千万不用重写finalzie方法
        M m = new M();
        m = null;

        System.gc();

        System.out.println(m);

        System.in.read();
    }

}
