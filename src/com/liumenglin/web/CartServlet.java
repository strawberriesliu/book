package com.liumenglin.web;

import com.google.gson.Gson;
import com.liumenglin.pojo.Book;
import com.liumenglin.pojo.Cart;
import com.liumenglin.pojo.CartItem;
import com.liumenglin.service.BookService;
import com.liumenglin.service.impl.BookServiceImpl;
import com.liumenglin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.liumenglin.web
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/5/6 20:06
 * Version 1.0
 */
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 将商品项加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);

        // 把图书信息转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));


    }

    /**
     * 删除购物车里的商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 调用Cart.deleteItem(id)删除商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if ( cart != null){
            cart.deleteItem(id);
        }
        // 重定向回购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 调用Cart.clear()清空购物车
        if ( cart != null){
            cart.clear();
        }
        // 重定向回购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改购物车中商品数量
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号,商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 调用Cart.updateCount(id, count)修改商品数量
        if ( cart != null){
            cart.updateCount(id, count);
        }
        // 重定向回购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);

        // 把图书信息转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 返回购物车总的商品数量和最后一个添加的商品项
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
