package com.waffae.pancake.integrated.interview.spring;

import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 *
 * @author yixiaoshuang
 * @date 2020/12/26 15:44
 */
public class TestMain {

    public static void main(String[] args) {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("classpath:/beanRefContext.xml");
        UserTestBean userTestBean = (UserTestBean) context.getBean("userTestBean");

        System.out.println(userTestBean.getName());

    }
}
