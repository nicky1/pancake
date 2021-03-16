package com.waffae.pancake.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yixiaoshuang
 * @date 2021/3/16 16:48
 */
@Configuration
@PropertySource(value = "classpath:lucy.properties")
@ConfigurationProperties(prefix = "lucy2")
@Data
public class Lucy2 {
    private String name;
    private String address;
}
