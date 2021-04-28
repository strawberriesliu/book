package com.liumenglin.service.impl;

import com.liumenglin.dao.UserDao;
import com.liumenglin.dao.impl.UserDaoImpl;
import com.liumenglin.pojo.User;
import com.liumenglin.service.UserService;

/**
 * Package: com.liumenglin.service.impl
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/10 22:14
 * Version 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPwd(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
