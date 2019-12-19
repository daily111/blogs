package com.dj.tool;

 public class Constant {
    // 短信应用 SDK AppID
    public static final int appid =1400256181; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    public static final String appkey ="b4282769231e7a187dd2d8bc30e80c99";
    // 需要发送短信的手机号码
    public static final String[] phoneNumbers ={"18570616802", "12345678902", "12345678903"};
    // 短信模板 ID，需要在短信应用中申请
    public static final int templateId =416727; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 签名
    public static final String smsSign ="崩说公众号"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请

    public static final int USER_STATUS_UNREGISTERED=0;

    public static final String USER_ALREADY_EXISTS="用户已存在，请登陆";

    public static final String VERICATION_CODE_ERROR="验证码错误,请重新输入";

    public static final int USER_STATUS_NORMAL=1;

    public static final String LOGIN_ERROR="账号或密码错误";

    public static final String PHONE_NULL="手机号不能不为空";

    public static final String ACCOUNT_OR_PASSWORD_NULL="账号或密码不能为空";

    public static final int PAGE_NO=1;

    public static final int PAGE_SIZE=15;

    public final static String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";//验证码key

    public final static String ACCESS_DENIED="权限不够，拒绝操作";

    /** 头像保存路径 */
    public static final String WINDOWS_PROFILES_PATH = "D:/images/";
    public static final String LINUX_PROFILES_PATH = "/usr/dj/imgs/";
    public static final String IMAGE = "image/";
    public static final String DEFAULT_IMAGE="image/timg.jpg";
 }
