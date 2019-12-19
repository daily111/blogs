package com.dj.tool;

import com.dj.dto.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 */
public class ShiroUtils {
    public static Session getSession(){
        //System.out.println(SecurityUtils.getSubject().getSession());
        Session session=SecurityUtils.getSubject().getSession();
        System.out.println(session);
        return session;

    }
    public static void setSessionAttribute(Object key,Object value){
        getSession().setAttribute(key,value);
    }

    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }
    public static Object getSessionAttribute(Object key) {
        System.out.println(getSession());
        System.out.println(getSession().getAttribute(key));
        return getSession().getAttribute(key);

    }
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    public static int getUserId() {
        return getUserEntity().getId();
    }
    public static User getUserEntity() {
        return (User)SecurityUtils.getSubject().getPrincipal();
    }
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
