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
 * @create 2020-07-19 10:20 上午
 */
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2. 检查验证码是否正确（暂时写死为abcde）
        if ("abcde".equalsIgnoreCase(code)) {
            // 正确
            // 3. 检查用户名是否可用
            if (!userService.existsUsername(username)) {
                userService.registerUser(new User(null, username, password, email));
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req, resp);
            } else {
                String errorMessage = "用户名[" + username + "]已存在";
                setReturnMessage(req, errorMessage, username, email);
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
            }


        } else {
            // 不正确
            // 跳转回注册页面
            String errorMessage = "验证码错误";
            setReturnMessage(req, errorMessage, username, email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    private void setReturnMessage(HttpServletRequest req, String errorMsg, String username, String email) {
        req.setAttribute("msg", errorMsg);
        req.setAttribute("username", username);
        req.setAttribute("email", email);
    }
}
