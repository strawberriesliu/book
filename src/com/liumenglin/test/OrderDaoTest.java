package com.liumenglin.test;

import com.liumenglin.dao.OrderDao;
import com.liumenglin.dao.impl.OrderDaoImpl;
import com.liumenglin.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 20:29
 * Version 1.0
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryOrders(){
        List<Order> orders = orderDao.queryOrders();
        for(Order order: orders){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus(){
        int i = orderDao.changeOrderStatus("16203943479891", 1);
        System.out.println(i);
    }

    @Test
    public void queryOrdersByUserId(){
        List<Order> orders = orderDao.queryOrdersByUserId(4);
        for(Order order : orders){
            System.out.println(order);
        }
    }
}