package com.liumenglin.service;

import com.liumenglin.pojo.Cart;
import com.liumenglin.pojo.Order;
import com.liumenglin.pojo.OrderItem;

import java.util.List;

/**
 * Package: com.liumenglin.service
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 19:53
 * Version 1.0
 */
public interface OrderService {

    String createOrder(Cart cart, Integer userId);

    List<Order> showAllOrders();

    void sendOrder(String orderId);

    List<OrderItem> showOrderDetail(String orderId);

    List<Order> showMyOrders(Integer userId);

    void receiverOrder(String orderId);


}
