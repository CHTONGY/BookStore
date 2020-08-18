package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-19 10:06 上午
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        if(!existsUsername(user.getUsername())) {
            userDao.saveUser(user);
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username) != null) {
            return true;
        } else {
            return false;
        }
    }
}
