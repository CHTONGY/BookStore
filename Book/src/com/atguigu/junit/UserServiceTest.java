package com.atguigu.junit;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-19 10:11 上午
 */
public class UserServiceTest {

    private UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "ty2", "tyty", "ty2@163.com"));
        userService.registerUser(new User(null, "ty", "tyty", "ty@163.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "ty", "tyty", null)));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("ty2"));
    }
}