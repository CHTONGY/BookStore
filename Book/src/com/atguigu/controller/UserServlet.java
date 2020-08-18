package com.atguigu.controller;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-21 7:21 下午
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//
//        try {
//             Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            method.invoke(this, req, resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        if("login".equalsIgnoreCase(action)) {
//////            login(req, resp);
//////            Method login = UserServlet.class.getDeclaredMethod("login", HttpServletRequest.class, HttpServletResponse.class);
////        } else if("register".equalsIgnoreCase(action)) {
////            register(req, resp);
////        }
//    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = new User(null, username, password, null);

        if(userService.login(loginUser) != null) {
            // 登陆成功
            // 将用户的信息保存在session域中

            req.getSession().setAttribute("user", userService.login(loginUser));
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        // 获取验证码，然后立刻删除
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 2. 检查验证码是否正确（暂时写死为abcde）
        if (token != null && token.equalsIgnoreCase(code)) {
            // 正确
            // 3. 检查用户名是否可用
            if (!userService.existsUsername(username)) {
                userService.registerUser(user);
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req, resp);
            } else {
                String errorMessage = "用户名[" + username + "]已存在";
                setReturnMessage(req, errorMessage, username, email);
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
            }
        } else {
            // 不正确
            // 跳转回注册页面
            String errorMessage;
            if(token == null) {
                errorMessage = "请勿重复提交表单";
            } else {
                errorMessage = "验证码错误";
            }
            setReturnMessage(req, errorMessage, username, email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. 销毁Session中的用户信息
        req.getSession().invalidate();
        // 2. 重定向至首页
        resp.sendRedirect(req.getContextPath());    // 直接重定向至工程目录，自动指向index.jsp
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. 获取请求的参数
        String username = req.getParameter("username");
        // 2. 调用userService
        boolean existsUsername = userService.existsUsername(username);
        // 3. 把返回的结果封装成为map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    private void setReturnMessage(HttpServletRequest req, String errorMsg, String username, String email) {
        req.setAttribute("msg", errorMsg);
        req.setAttribute("username", username);
        req.setAttribute("email", email);
    }

}
