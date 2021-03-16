package com.waffae.pancake.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yixiaoshuang
 * @date 2021/3/16 16:48
 */
@ConfigurationProperties(prefix = "lucy")
@Component
@Data
public class Lucy {
    private String name;
}
