package com.waffae.pancake.integrated.interview.spring;

import org.springframework.stereotype.Component;

/**
 * @author yixiaoshuang
 * @date 2020/12/26 15:13
 */
@Component
public class UserTestBean {

    private String name;

    private int age;

    public String getTheName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
