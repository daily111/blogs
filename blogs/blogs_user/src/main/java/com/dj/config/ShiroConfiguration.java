package com.dj.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @ClassName: ShiroConfiguration
 * @Description: shiro的配置类
 * @author fuweilian
 * @date 2018-5-12 上午11:05:09
 */
@Configuration
public class ShiroConfiguration {
    private static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        logger.info("进入shiroFilter......");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置不需要拦截的路径
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //按顺序依次判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/home/**", "anon");
        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("/**/**.js", "anon");
        filterChainDefinitionMap.put("/**/**.css", "anon");
        filterChainDefinitionMap.put("/**/**.jpg", "anon");
        filterChainDefinitionMap.put("/**/**.png", "anon");
        filterChainDefinitionMap.put("/T1.html","anon");
        //先关闭数据权限，取消登录
       filterChainDefinitionMap.put("/**/**", "anon");
        filterChainDefinitionMap.put("/**", "anon");
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        /************************************初始化所有的权限信息开始******************************************/
        //这里，如果以后再项目中使用的话，直接从数据库中查询
        filterChainDefinitionMap.put("/**/list", "authc,perms[user:list]");
        filterChainDefinitionMap.put("/**/save", "authc,perms[user:save]");
        //filterChainDefinitionMap.put("/user/add", "authc,perms[user:add]");
        /***************************************初始化所有的权限信息开始结束*********************************************/
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/home/denied");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //后面这里可以设置缓存的机制
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}