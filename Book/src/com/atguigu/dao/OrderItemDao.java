package com.atguigu.dao;

import com.atguigu.bean.OrderItem;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:17 下午
 */
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
}
