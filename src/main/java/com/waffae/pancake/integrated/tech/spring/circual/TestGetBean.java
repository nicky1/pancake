package com.waffae.pancake.integrated.tech.spring.circual;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试spring如何处理循环依赖：单例/原型模式
 *
 * @author yixiaoshuang
 * @date 2021/3/21 15:46
 */
public class TestGetBean {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        A a = context.getBean("A", A.class);
        System.out.println(ToStringBuilder.reflectionToString(a));

        A a2 = context.getBean("A", A.class);
        System.out.println(ToStringBuilder.reflectionToString(a2));
    }

}
