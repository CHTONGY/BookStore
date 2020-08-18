package com.atguigu.service;

import com.atguigu.bean.Cart;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:29 下午
 */
public interface OrderService {
    String createOrder(Cart cart, Integer userId);
}
