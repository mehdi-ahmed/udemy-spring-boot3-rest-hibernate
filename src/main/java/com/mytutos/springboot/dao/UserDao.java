package com.mytutos.springboot.dao;

import com.mytutos.springboot.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
}
