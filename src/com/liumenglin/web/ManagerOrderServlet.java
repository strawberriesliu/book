package com.liumenglin.web;

import com.liumenglin.pojo.Order;
import com.liumenglin.pojo.OrderItem;
import com.liumenglin.service.OrderService;
import com.liumenglin.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Package: com.liumenglin.web
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/8 16:14
 * Version 1.0
 */
public class ManagerOrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 查看所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、通过OrderService查询全部订单
        List<Order> orders = orderService.showAllOrders();
        // 2、把全部订单保存到Request域中
        req.setAttribute("orders", orders);
        // 3、请求转发到/pages/manager/order_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }


    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取请求的参数 订单编号
        String orderId = req.getParameter("orderId");
        //调用orderService.sendOrder(orderId)发货
        orderService.sendOrder(orderId);
        // 重定向回订单展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    /**
     * 查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取请求的参数 订单编号
        String orderId = req.getParameter("orderId");
        // 通过OrderService当前用户查询全部订单
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        // 2、把全部订单保存到Request域中
        req.setAttribute("orderItems", orderItems);
        // 3、请求转发到/pages/manager/order_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/order_item.jsp").forward(req, resp);
    }

}
