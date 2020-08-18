package com.atguigu.controller;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-20 10:01 上午
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(userService.login(new User(null, username, password, null)) != null) {
            // 登陆成功
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        }
    }
}
