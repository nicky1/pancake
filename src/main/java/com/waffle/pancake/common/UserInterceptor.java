package com.waffle.pancake.common;

import com.waffle.pancake.dto.UserInfo;
import com.waffle.pancake.util.thread.ThreadContext;
import com.waffle.pancake.util.thread.ThreadContext2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
@Slf4j
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getParameter("userId");
        if (StringUtils.isNotBlank(userId)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(Integer.valueOf(userId));
            ThreadContext.bindUser(userInfo);

            ThreadContext2.set(userId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadContext.unBindUser();
        ThreadContext2.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
