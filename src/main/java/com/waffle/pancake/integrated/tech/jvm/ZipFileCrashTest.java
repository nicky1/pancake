package com.waffle.pancake.integrated.tech.jvm;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * https://cloud.tencent.com/developer/article/1039901
 * 线上应用crash
 * 1.jvm应用要在linux系统上运行
 * 2.通过echo命令,修改zip文件内容
 *
 * @author yixiaoshuang
 * @date 2021/8/25 18:44
 */
public class ZipFileCrashTest {

    public static void unzip(long interval, String fileName) {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileName);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()){
                ZipEntry zipEntry = entries.nextElement();
                System.out.println(zipEntry.getName());

                TimeUnit.SECONDS.sleep(interval);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
