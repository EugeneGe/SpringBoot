package com.gly.springboot.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.gly.springboot.config.jwt.entity.SecurityConstants;
import com.gly.springboot.config.jwt.utils.JwtUtils;
import com.gly.springboot.entity.sys.LoginUser;
import com.gly.springboot.redis.RedisDao;
import com.gly.springboot.utils.IdUtils;
import com.gly.springboot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author smartever
 */
@Component
public class TokenService {
    @Autowired
    private RedisDao redisDao;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginUser loginUser) {
        String token = IdUtils.fastUUID();
        Long userId = loginUser.getSysUser().getId();
        String loginAccount = loginUser.getSysUser().getLoginAccount();
        loginUser.setToken(token);
        loginUser.setUserid(userId);
        loginUser.setLoginAccount(loginAccount);
//        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_LOGINACCOUNT, loginAccount);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in", SecurityConstants.EXPIRATION);
        return rspMap;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
//    public LoginUser getLoginUser() {
//        return getLoginUser(ServletUtils.getRequest());
//    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
//    public LoginUser getLoginUser(HttpServletRequest request) {
//        // 获取请求携带的令牌
//        String token = SecurityUtils.getToken(request);
//        return getLoginUser(token);
//    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String token) {
        LoginUser user = null;
        try {
            if (StringUtils.isNotEmpty(token)) {
                String userkey = JwtUtils.getUserKey(token);
                user = JSONObject.parseObject(redisDao.getCacheObject(getTokenKey(userkey)), LoginUser.class);
            }
            return user;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户缓存信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userkey = JwtUtils.getUserKey(token);
            redisDao.deleteObject(getTokenKey(userkey));
        }
    }

    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= SecurityConstants.REFRESH_TIME) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + SecurityConstants.REFRESH_TIME);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisDao.setCacheObject(userKey, JSONObject.toJSONString(loginUser), SecurityConstants.EXPIRATION, TimeUnit.MINUTES);
    }

    private String getTokenKey(String token) {
        return SecurityConstants.LOGIN_TOKEN_KEY + token;
    }
}
