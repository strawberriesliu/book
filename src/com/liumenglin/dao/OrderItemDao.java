package com.liumenglin.dao;

import com.liumenglin.pojo.OrderItem;

import java.util.List;

/**
 * Package: com.liumenglin.dao
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 19:59
 * Version 1.0
 */
public interface OrderItemDao {

    int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
