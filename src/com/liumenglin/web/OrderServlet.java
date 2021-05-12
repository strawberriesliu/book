package com.liumenglin.web;

import com.liumenglin.pojo.*;
import com.liumenglin.service.BookService;
import com.liumenglin.service.OrderService;
import com.liumenglin.service.impl.BookServiceImpl;
import com.liumenglin.service.impl.OrderServiceImpl;
import com.liumenglin.utils.JdbcUtils;
import com.liumenglin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Package: com.liumenglin.web
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/7 19:46
 * Version 1.0
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    private BookService bookService = new BookServiceImpl();

    /**
     * 生成订单
      * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if(loginUser == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();

        Map<Integer, CartItem> items = cart.getItems();
        for(Map.Entry<Integer, CartItem> entry : items.entrySet()){
            String bookName = entry.getValue().getName();
            int bookStock = bookService.queryBookByName(bookName).getStock();
            if(bookStock < entry.getValue().getCount()){
                req.getSession().setAttribute("bookName", bookName);
                req.getSession().setAttribute("bookStock", bookStock);
                resp.sendRedirect(req.getContextPath() + "/pages/cart/stock.jsp");
                return;
            }
        }

        // 调用orderService.createOrder(cart, userId)生成订单
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);

        // 重定向到/pages/cart/checkout.jsp(重定向可以防止重复提交)
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

    }

    /**
     * 查看我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取请求的参数 订单编号
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        // 1、通过OrderService查询我的全部订单
        List<Order> myOrders = orderService.showMyOrders(userId);
        // 2、把全部订单保存到Request域中
        req.setAttribute("myOrders", myOrders);
        // 3、请求转发到/pages/manager/order_manager.jsp页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
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
        // 3、请求转发到/pages/order/order_item.jsp页面
        req.getRequestDispatcher("/pages/order/order_item.jsp").forward(req, resp);
    }



    /**
     * 签收订单/确认收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取请求的参数 订单编号
        String orderId = req.getParameter("orderId");
        //调用orderService.sendOrder(orderId)发货
        orderService.receiverOrder(orderId);
        // 重定向回订单展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
