package com.waffle.pancake.integrated.tech.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试自定义的类加载器实例化出来的类,和默认的jvm虚拟机加载的类 是不相等的。
 *
 * @author yixiaoshuang
 * @date 2020/12/14 19:08
 */
public class ClassLoadTest {

    private int a;

    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("com.waffle.integrated.interview.jvm.ClassLoadTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoadTest);
    }

}
