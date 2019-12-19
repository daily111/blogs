package com.dj.service.lmpl;

import com.dj.dto.PageDto;
import com.dj.dto.QueryUser;
import com.dj.dto.User;
import com.dj.mapper.LoginMap;
import com.dj.service.LoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("LoginServiceImpl")
public class LoginServiceImpl implements LoginService {

    @Resource(name = "LoginMap")
    LoginMap loginMap;

    @Override
    public Set<String> listUserRoles(Integer userId) {
        //List<String> roles = sampleServiceMap.listUserRoles(userId);
        Set<String> rolesSet = new HashSet<>();
        User user=loginMap.listUserRoles(userId);
        /*for(String role : roles) {
            if(StringUtils.isNotBlank(role)) {
                rolesSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }*/
        rolesSet.add(user.getRoleName());
        return rolesSet;
    }

    @Override
    public Set<String> listUserPerms(Integer roleId) {
        Set<String> set=loginMap.listUserPerms(roleId);
        return set;
    }

    @Override
    public User getByUserName(User user) {
        User resultUser = loginMap.getByUserName(user);

        return resultUser;
    }

    @Override
    public PageDto<User> list(QueryUser query) {
        PageDto<User> result = new PageDto<>();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());

        List<User> userList = loginMap.list(query);
        PageInfo<User> p = new PageInfo<>(userList);

        result.setTotal(p.getTotal());
        result.setItems(userList);
        return result;
    }

}
