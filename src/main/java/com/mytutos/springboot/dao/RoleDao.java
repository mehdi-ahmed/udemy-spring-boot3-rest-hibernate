package com.mytutos.springboot.dao;

import com.mytutos.springboot.entity.Role;

public interface RoleDao {

    Role findRoleByName(String theRoleName);
}
