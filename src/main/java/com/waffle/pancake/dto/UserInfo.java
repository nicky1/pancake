package com.waffle.pancake.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
@Data
public class UserInfo implements Serializable {
    private Integer userId;
    private String userName;
}
