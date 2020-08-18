package com.atguigu.controller;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:43 下午
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取购物车cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取userid
        User loginUser = (User) req.getSession().getAttribute("user");
        if(loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer id = loginUser.getId();
        // 调用createOrder
        String orderId = orderService.createOrder(cart, id);

        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
