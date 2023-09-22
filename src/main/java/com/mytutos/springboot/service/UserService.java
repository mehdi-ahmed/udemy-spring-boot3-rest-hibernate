package com.mytutos.springboot.service;

import com.mytutos.springboot.entity.User;
import com.mytutos.springboot.entity.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(WebUser webUser);
}
