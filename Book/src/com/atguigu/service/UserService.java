package com.atguigu.service;

import com.atguigu.bean.User;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-19 10:03 上午
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登陆
     * @param user
     * @return 返回null表示登陆失败
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户民已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
