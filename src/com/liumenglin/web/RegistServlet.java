package com.liumenglin.web;

import com.liumenglin.pojo.User;
import com.liumenglin.service.UserService;
import com.liumenglin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * Package: com.liumenglin.web
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/11 21:52
 * Version 1.0
 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2、检查验证码是否正确
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if(token != null && token.equalsIgnoreCase(code)){
            // 验证码正确
            // 3、检查用户名是否可用（调用userService的方法existsUsername检查）
            if(userService.existsUsername(username)){
                System.out.println("用户名[" + username + "]已存在！");

                // 把回显信息保存到request域中
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                // 不可用，跳回注册界面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            } else{
                // 可用
                //调用UserService类的方法registUser保存到数据库
                userService.registUser(new User(null, username, password, email));

                // 跳到注册成功页面regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {
            // 把回显信息保存到request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            // 验证码不正确，跳回注册页面
            System.out.println("验证码[" + code +"]错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
