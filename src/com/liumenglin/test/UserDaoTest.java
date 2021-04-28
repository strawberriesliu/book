package com.liumenglin.test;

import com.liumenglin.dao.UserDao;
import com.liumenglin.dao.impl.UserDaoImpl;
import com.liumenglin.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/10 21:36
 * Version 1.0
 */
public class UserDaoTest {
    public UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin12") == null){
            System.out.println("用户名可用！");
        } else{
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "lisi", "111", "lisi@163.com")));
    }

    @Test
    public void queryUserByUsernameAndPwd() {
        if(userDao.queryUserByUsernameAndPwd("admin", "admin") == null){
            System.out.println("您输入的用户名或密码有误，登录失败");
        } else{
            System.out.println("登录成功！");
        }
    }
}