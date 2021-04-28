package com.liumenglin.service;

import com.liumenglin.pojo.User;

/**
 * Package: com.liumenglin.service.impl
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/10 22:10
 * Version 1.0
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null说明登录失败，返回其他则说明登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用+
     */
    public boolean existsUsername(String username);


}
