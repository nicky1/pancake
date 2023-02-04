package com.waffle.pancake.service.student;

import com.waffle.pancake.dto.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2023/2/4
 **/
@Service
public class UserService {

    public UserInfo getUser(Integer userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserName("zs");
        return userInfo;
    }

}
