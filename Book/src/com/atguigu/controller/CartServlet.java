package com.atguigu.controller;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 10:41 上午
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Book book = bookService.queryBookById(id);

        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 将购物车保存在Session中
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName", cartItem.getName());

        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Book book = bookService.queryBookById(id);

        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 将购物车保存在Session中
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName", cartItem.getName());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);

    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2. 得到购物车session
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 3. 删除item
        cart.deleteItem(id);
        // 4. 重定向回cart.jsp
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 得到当前购物车的Session
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 2. 将购物车中的items Map清空
        cart.clear();
        // 3. 重定向回cart.jsp
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 得到需要修改的id与修改后的count
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        // 2. 从session中获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 3. 根据id找到对应item
        CartItem item = cart.getItems().get(id);
        // 4. 设置item的count与totalPrice
        item.setCount(count);
        item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        // 5. 重定向回cart.jsp
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
