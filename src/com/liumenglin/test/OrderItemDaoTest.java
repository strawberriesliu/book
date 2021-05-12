package com.liumenglin.test;

import com.liumenglin.dao.OrderItemDao;
import com.liumenglin.dao.impl.OrderItemDaoImpl;
import com.liumenglin.pojo.OrderItem;
import org.junit.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 20:35
 * Version 1.0
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {


        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通", 1,
                new BigDecimal(100), new BigDecimal(100), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javadcript从入门到精通", 2,
                new BigDecimal(50), new BigDecimal(100), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "c++从入门到精通", 1,
                new BigDecimal(80), new BigDecimal(80), "1234567890"));
    }

    @Test
    public void queryOrderItemsByOrderId(){
        List<OrderItem> orderItemList = orderItemDao.queryOrderItemsByOrderId("16203943479891");
        for(OrderItem orderItem: orderItemList){
            System.out.println(orderItem);
        }
    }
}