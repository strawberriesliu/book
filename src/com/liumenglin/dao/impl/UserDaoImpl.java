package com.liumenglin.dao.impl;

import com.liumenglin.dao.UserDao;
import com.liumenglin.pojo.User;

/**
 * Package: com.liumenglin.dao.impl
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/10 21:27
 * Version 1.0
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";

        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPwd(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }
}
