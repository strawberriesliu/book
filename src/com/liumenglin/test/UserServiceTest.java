package com.liumenglin.test;

import com.liumenglin.pojo.User;
import com.liumenglin.service.UserService;
import com.liumenglin.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/10 22:20
 * Version 1.0
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "admin", "666", "zs@168.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "zs", "666", null)));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("88")){
            System.out.println("用户名已存在");
        } else{
            System.out.println("用户名可用！");
        }
    }
}