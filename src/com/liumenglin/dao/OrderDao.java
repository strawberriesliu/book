package com.liumenglin.dao;

import com.liumenglin.pojo.Order;
import com.liumenglin.pojo.OrderItem;

import java.util.List;

/**
 * Package: com.liumenglin.dao
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 19:58
 * Version 1.0
 */
public interface OrderDao {

    int saveOrder(Order order);

    List<Order> queryOrders();

    int changeOrderStatus(String orderId, Integer status);

    List<Order> queryOrdersByUserId(Integer userId);
}
