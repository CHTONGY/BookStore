package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-25 9:25 上午
 */
public class Cart {
    private Map<Integer, CartItem> items;

    public Cart() {
        items = new LinkedHashMap<>();
    }

    /**
     * 添加商品
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if(item != null) {
            // 如果购物车中已经包含该商品，则改变该商品的数量与总价格
            item.setCount(item.getCount() + cartItem.getCount());
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            items.put(item.getId(),item);
        } else {
            // 如果购物车不包含该商品，则加入购物车
            items.put(cartItem.getId(),cartItem);
        }
    }

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        CartItem item = items.get(id);
        if(item.getCount() == 1) {
            items.remove(id);
        } else {
            item.setCount(item.getCount() - 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        item.setCount(count);
        item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        items.put(id,item);
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(CartItem item : items.values()) {
            totalCount += item.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(CartItem item : items.values()) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
