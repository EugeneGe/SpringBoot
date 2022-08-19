package com.gly.springboot.config.jwt.entity;

/**
 * Token的Key常量
 *
 * @author smartever
 */
public class SecurityConstants {
    /*----------------------------------- token相关 --------------------------------*/
    /**
     * 令牌自定义标识
     */
    public static final String AUTHENTICATION = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String PREFIX = "Bearer ";

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "abcdefghijklmnopqrstuvwxyz";

    /**
     * SESSION
     */
    public final static String SESSION_ID = "SESSIONID";

    /**
     * SESSION
     */
    public final static String RESOURCES_ID = "RESOURCESID";


    /*----------------------------------- 权限相关 --------------------------------*/
    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    public static final String DETAILS_LOGINACCOUNT = "loginAccount";

    /**
     * 授权信息字段
     */
    public static final String AUTHORIZATION_HEADER = "authorization";

    /**
     * 请求来源
     */
    public static final String FROM_SOURCE = "from-source";

    /**
     * 内部请求
     */
    public static final String INNER = "inner";

    /**
     * 用户标识
     */
    public static final String USER_KEY = "user_key";

    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "login_user";


    /*----------------------------------- 缓存常量相关 --------------------------------*/
    /**
     * 缓存有效期，默认720（分钟）
     */
    public final static long EXPIRATION = 720000;

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public final static long REFRESH_TIME = 120;

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";
}
