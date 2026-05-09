package com.waffle.pancake.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2025/6/17
 **/
public class NativeLibraryLoader {
    public static void loadLibrary(String libraryName) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        String extension;

        if (os.contains("win")) {
            extension = ".dll";
        } else if (os.contains("mac")) {
            extension = ".dylib";
        } else {
            extension = ".so";
        }

        String fullName = "lib" + libraryName + extension;
        String resourcePath = "/lib/" + fullName;

        try (InputStream is = NativeLibraryLoader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new FileNotFoundException("资源未找到: " + resourcePath);
            }

            // 创建临时文件
            Path tempFile = Files.createTempFile(fullName, "");
            Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);

            // 加载库
            System.load(resourcePath);
            System.out.println("从临时文件加载: " + tempFile);

            // 退出时删除临时文件
            tempFile.toFile().deleteOnExit();
        }
    }
}
