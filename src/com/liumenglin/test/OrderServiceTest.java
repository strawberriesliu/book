package com.liumenglin.test;

import com.liumenglin.pojo.Cart;
import com.liumenglin.pojo.CartItem;
import com.liumenglin.pojo.Order;
import com.liumenglin.pojo.OrderItem;
import com.liumenglin.service.OrderService;
import com.liumenglin.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 21:05
 * Version 1.0
 */
public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));

        orderService.createOrder(cart, 1);
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1));
    }

    @Test
    public void sendOrder(){
        orderService.sendOrder("16203943479891");
    }

    @Test
    public void showOrderDetail(){
        List<OrderItem> orderItems = orderService.showOrderDetail("16203943479891");
        for(OrderItem orderItem: orderItems){
            System.out.println(orderItem);
        }
    }

    @Test
    public void showMyOrders(){
        List<Order> orders = orderService.showMyOrders(1);
        for(Order order : orders){
            System.out.println(order);
        }
    }

    @Test
    public void receiverOrder(){
        orderService. receiverOrder("16203943479891");
    }
}