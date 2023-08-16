package com.waffle.pancake.service.student;

import com.alibaba.testable.core.annotation.MockInvoke;
import com.waffle.pancake.dto.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.alibaba.testable.core.matcher.InvocationVerifier.verifyInvoked;


@Slf4j
public class StudentServiceTest {
   private StudentService studentService = new StudentService();


    @Test
    public void testGetUser() {

        UserInfo user = studentService.user(123);

        // verify: mock方法getUser是否被调用到,且调用时接收到的参数值是否为123
        verifyInvoked("getUser").with(123);
    }


    public static class Mock {

        @MockInvoke(targetClass = com.waffle.pancake.service.student.UserService.class)
        private UserInfo getUser(Integer userId) {
            log.info("start mock");
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(123);
            userInfo.setUserName("ls");
            return userInfo;
        }
    }
}
