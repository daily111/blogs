package com.dj.controller;


import com.dj.dto.PageDto;
import com.dj.dto.QueryUser;
import com.dj.dto.User;
import com.dj.tool.Parameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/login")
public interface LoginController {

    /**
     * 验证码
     */
    @CrossOrigin(origins = "*")
    @RequestMapping("/captcha.jpg")
     void captcha(HttpServletResponse response);

    /**
     * 登陆
     * @param user
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public Parameters<User> login(User user);

    /**
     * 用户列表接口
     * @param query
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping ("/list")
    public Parameters<PageDto<User>> list(QueryUser query);
}
