package com.dj.controller.lmpl;

import com.dj.controller.LoginController;
import com.dj.dto.PageDto;
import com.dj.dto.QueryUser;
import com.dj.dto.User;
import com.dj.service.LoginService;
import com.dj.tool.Constant;
import com.dj.tool.Parameters;
import com.dj.tool.ShiroUtils;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAppConfiguration
public class LoginControllerImpl implements LoginController {
    @Autowired
    private HttpServletRequest request; // 自动注入request

    @Autowired
    private Producer producer;

    @Autowired
    private LoginService loginService;

    @Override
    public void captcha( HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");
        //使用验证码插件生成验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

        ShiroUtils.setSessionAttribute(Constant.KAPTCHA_SESSION_KEY, text);
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);

        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {

        }

    }

    @Override
    public Parameters<User> login(@RequestBody User user) {
        String username = user.getAccount();
        String password = user.getPassWord();
        Parameters<User> response;
        String kaptcha = null;
        if (user.getVerificationCode() != null && !user.getVerificationCode().isEmpty()) {
            try {
                kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
            } catch (Exception e) {
                response = Parameters.fail();
                response.setMsg("验证码已过期,请刷新验证码");
                return response;
            }
        }

        if (!user.getVerificationCode().equalsIgnoreCase(kaptcha)) {
            response = Parameters.fail();
            response.setMsg(Constant.VERICATION_CODE_ERROR);
            return response;
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            //password = MD5Utils.encrypt(username, password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            response = Parameters.fail();
            response.setMsg(e.getMessage());
            return response;
        } catch (LockedAccountException e) {
            response = Parameters.fail();
            response.setMsg(e.getMessage());
            return response;
        } catch (AuthenticationException e) {
            response = Parameters.fail();
            response.setMsg("账户验证失败");
            return response;
        }
        response = Parameters.ok();
        return response;
    }

    @Override
    public Parameters<PageDto<User>> list(@RequestBody QueryUser query) {
        Parameters<PageDto<User>> response = new Parameters<>();


        PageDto<User> data = loginService.list(query);

        response.setData(data);
        return response;

    }
}
