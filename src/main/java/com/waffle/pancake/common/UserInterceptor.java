package com.waffle.pancake.common;

import com.waffle.pancake.dto.UserInfo;
import com.waffle.pancake.util.thread.ThreadContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

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
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TimeUnit.MILLISECONDS.sleep(5);
        ThreadContext.unBindUser();
        super.afterCompletion(request, response, handler, ex);
    }
}
