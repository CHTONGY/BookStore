package com.atguigu.dao;

import com.atguigu.bean.User;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-19 9:23 上午
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 返回null说明没有这个用户。
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 返回null说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示添加失败
     */
    public int saveUser(User user);
}
