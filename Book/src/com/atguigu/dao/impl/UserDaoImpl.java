package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-19 9:28 上午
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where `username` = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where `username` = ? and `password` = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`, `password`, `email`) values(?,?,?)";
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        return update(sql, username, password, email);
    }
}
