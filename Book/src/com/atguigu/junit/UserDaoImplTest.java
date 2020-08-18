package com.atguigu.junit;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-19 9:37 上午
 */
public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("admin", "admin"));
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "ty", "tyty", "ty@163.com")));
    }
}