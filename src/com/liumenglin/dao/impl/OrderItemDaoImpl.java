package com.liumenglin.dao.impl;

import com.liumenglin.dao.OrderItemDao;
import com.liumenglin.pojo.OrderItem;

import java.util.List;

/**
 * Package: com.liumenglin.dao.impl
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 20:24
 * Version 1.0
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `price`,`total_money`,`count`, `order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getPrice(), orderItem.getTotalPrice(),
                orderItem.getCount(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `name`, `price`,`total_money` totalPrice,`count`, `order_id` orderId " +
                "from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
