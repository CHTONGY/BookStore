package com.atguigu.dao;

import com.atguigu.bean.Order;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:16 下午
 */
public interface OrderDao {
    int saveOrder(Order order);
}
