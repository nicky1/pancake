package com.waffle.pancake.integrated.tech.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 测试BeanFactoryPostProcessor和BeanPostProcessor的区别
 * 1.BeanFactoryPostProcessor 是在spring容器加载了bean定义文件之后，bean实例化之前执行的。
 * 2.可以在bean实例化之前进行自定义的扩展,
 * 3.而 BeanPostProcessor 是在bean 实例化之后，bean初始化之前进行的。
 * 是在 BeanFactoryPostProcessor 之后执行的。
 *
 * @author yixiaoshuang
 * @date 2020/12/26 14:38
 */
@Slf4j
@Component
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {

    /**
     * 1.这里测试遇到一个问题：使用@component注解注入的bean,获取不到它的属性
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        log.info("call BeanFactoryPostProcessor ");

        BeanDefinition bd = beanFactory.getBeanDefinition("userTestBean");

        log.info("properties:{}", bd.getPropertyValues().toString());

        MutablePropertyValues propertyValues = bd.getPropertyValues();
        if (propertyValues.contains("name")) {
            propertyValues.addPropertyValue("name", "李四");
        }

    }
}
