package com.atguigu.dao.impl;

import com.atguigu.bean.Order;
import com.atguigu.dao.OrderDao;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:18 下午
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id, create_time, price, status, user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }
}
