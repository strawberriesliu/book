package com.liumenglin.service.impl;

import com.liumenglin.dao.BookDao;
import com.liumenglin.dao.OrderDao;
import com.liumenglin.dao.OrderItemDao;
import com.liumenglin.dao.impl.BookDaoImpl;
import com.liumenglin.dao.impl.OrderDaoImpl;
import com.liumenglin.dao.impl.OrderItemDaoImpl;
import com.liumenglin.pojo.*;
import com.liumenglin.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Package: com.liumenglin.service.impl
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 20:45
 * Version 1.0
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成订单项保存到数据库
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            // 获取每个购物车的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);
            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        // 清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        List<Order> orders = orderDao.queryOrdersByUserId(userId);
        return orders;
    }

    @Override
    public void receiverOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 2);
    }
}
