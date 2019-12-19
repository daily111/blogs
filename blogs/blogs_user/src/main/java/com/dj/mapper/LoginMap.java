package com.dj.mapper;

import com.dj.dto.QueryUser;
import com.dj.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository("LoginMap")
@Mapper
public interface LoginMap {
    User getByUserName(User user);

    User listUserRoles(@Param("userId") Integer userId);

    Set<String> listUserPerms(@Param("roleId") Integer roleId);

    List<User> list(QueryUser query);

}
