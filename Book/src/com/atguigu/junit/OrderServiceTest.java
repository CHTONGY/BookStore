package com.atguigu.junit;

import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:40 下午
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));

        System.out.println(orderService.createOrder(cart, 1));
    }
}