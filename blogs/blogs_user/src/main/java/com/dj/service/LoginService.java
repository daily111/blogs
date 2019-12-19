package com.dj.service;

import com.dj.dto.PageDto;
import com.dj.dto.QueryUser;
import com.dj.dto.User;

import java.util.Set;

public interface LoginService {

    Set<String> listUserRoles(Integer userId);

    Set<String> listUserPerms(Integer roleId);

    User getByUserName(User user);

    PageDto<User> list(QueryUser query);
}
