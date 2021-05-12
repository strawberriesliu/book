package com.liumenglin.dao.impl;

import com.liumenglin.dao.OrderDao;
import com.liumenglin.pojo.Order;
import com.liumenglin.pojo.OrderItem;

import java.util.List;

/**
 * Package: com.liumenglin.dao.impl
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 20:19
 * Version 1.0
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(),
                order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId,`create_time` createTime,`total_money` price," +
                "`status`,`user_id` userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set status = ? where order_id = ?";
        return update(sql, status, orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select `order_id` orderId,`create_time` createTime,`total_money` price," +
                "`status`,`user_id` userId from t_order where user_id = ?";
        return queryForList(Order.class, sql, userId);
    }
}
