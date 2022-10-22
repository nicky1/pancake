package com.waffle.pancake.config;

import com.waffle.pancake.common.UserInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/api/**","/test/**");
    }
}
