package com.waffle.pancake.controller;

import com.waffle.pancake.integrated.tech.jvm.ZipFileCrashTest;
import com.waffle.pancake.service.pg.LclTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2021/8/26 20:04
 */
@RestController
@Slf4j
public class TestController {
    @Resource
    private LclTest lclTest;

    @GetMapping(value = "/api/v1/zip/crash/test")
    public ResponseEntity testZipCrash(@RequestParam Long interval, @RequestParam String fileName) {
        String zipFileName = "/Users/xiaoshuangyi/logs/bffa0bfb6e79482896d1636766eb8233.zip";
        if (StringUtils.isEmpty(fileName)){
            fileName = zipFileName;
        }
        ZipFileCrashTest.unzip(interval, fileName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/byjp")
    public ResponseEntity testByJp(@RequestParam String filePath) {
        log.info("filePath :{}" , filePath);
        try {
            lclTest.testImportFeedBill(filePath);
        } catch (Exception e) {
            log.info("error:" , e);

        }
        return ResponseEntity.ok().build();
    }

}
