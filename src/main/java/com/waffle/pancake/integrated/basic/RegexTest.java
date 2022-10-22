package com.waffle.pancake.integrated.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc: 测试正则表达式
 * @author: yixiaoshuang
 * @date: 2022/8/22
 **/
public class RegexTest {


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            test1();
        }
    }

    public static void test2() {
        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~_%\\/])+$";
        String bugUrl = "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        if (bugUrl.matches(badRegex)) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }
    }

    public static void test1() {
        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$";
        String bugUrl = "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        Pattern compile = Pattern.compile(badRegex);
        Matcher matcher = compile.matcher(bugUrl);
        if (matcher.matches()) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }
    }
}
