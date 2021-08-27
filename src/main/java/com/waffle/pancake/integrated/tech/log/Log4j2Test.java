package com.waffle.pancake.integrated.tech.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author yixiaoshuang
 * @date 2021/8/24 15:34
 */
public class Log4j2Test {


    private static final Logger log = LogManager.getLogger(Log4j2Test.class);



    public static void main(String[] args) {
        log.info("111");




    }

}
