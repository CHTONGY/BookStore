package com.atguigu.junit;

import com.atguigu.bean.Order;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 8:25 下午
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("123", new Date(), new BigDecimal(100), 0, 1));
        System.out.println(orderDao);
    }
}