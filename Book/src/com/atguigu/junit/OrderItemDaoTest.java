package com.atguigu.junit;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:28 下午
 */
public class OrderItemDaoTest {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null, "admin", 1, new BigDecimal(10), new BigDecimal(20), "123"));
    }
}