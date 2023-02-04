package com.waffle.pancake.service.student;

import com.alibaba.fastjson.JSON;
import com.waffle.pancake.dto.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2023/2/4
 **/
@Service
@Slf4j
public class StudentService {

    @Resource
    private UserService userService;

    public UserInfo user(Integer userId) {
        UserInfo user = userService.getUser(userId);
        log.info("user:{}", JSON.toJSON(user));

        return user;
    }

}
