package com.atguigu.junit;

import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 10:22 上午
 */
public class CartTest {
    Cart cart = new Cart();

    @Test
    public void addItem() {
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));

        System.out.println(cart);

    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));

        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));

        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(1) ,new BigDecimal(1)));

        cart.updateCount(1,10);
        System.out.println(cart);
    }
}