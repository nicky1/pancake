package com.waffle.pancake.model.mongodb;

import lombok.Builder;
import lombok.Data;

/**
 * @author yixiaoshuang
 * @date 2019-10-11 23:24
 */
@Builder
@Data
public class Work {

    private String id;

    private String name;

    private long createTime;


}
