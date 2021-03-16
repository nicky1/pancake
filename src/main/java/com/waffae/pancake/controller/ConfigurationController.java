package com.waffae.pancake.controller;

import com.waffae.pancake.model.properties.Lucy;
import com.waffae.pancake.model.properties.Lucy2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * spring boot configuration test
 * 1.测试发现不添加@EnableConfigurationProperties注解也是能访问到自定义配置文件属性的。但自定义配置文件要是properties格式的。
 *
 * @author yixiaoshuang
 * @date 2021/3/16 16:53
 */
@RestController
@EnableConfigurationProperties({Lucy2.class})
public class ConfigurationController {

    @Resource
    private Lucy lucy;

    @Resource
    private Lucy2 lucy2;

    @GetMapping(value = "/api/v1/config/getbean")
    public ResponseEntity getConfigFromBean(){
        String name =lucy.getName();
        return ResponseEntity.ok(name);
    }

    /**
     * 配置从自定义配置文件获取
     */
    @GetMapping(value = "/api/v1/config/getbean2")
    public ResponseEntity getConfigFromBean2(){
        String name =lucy2.getName()+";"+lucy2.getAddress();
        return ResponseEntity.ok(name);
    }
}
