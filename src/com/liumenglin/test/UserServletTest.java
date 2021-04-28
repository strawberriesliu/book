package com.liumenglin.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/19 15:17
 * Version 1.0
 */
public class UserServletTest {

    public void login(){
        System.out.println("login()方法调用了！");
    }

    public void regist(){
        System.out.println("regist()方法调用了！");
    }

    public void updateUser(){
        System.out.println("updateUser()方法调用了！");
    }

    public void updateUserPassword(){
        System.out.println("updateUserPassword()方法调用了！");
    }

    public static void main(String[] args) {
        String action = "regist";

        try {
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);

            method.invoke(new UserServletTest());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
