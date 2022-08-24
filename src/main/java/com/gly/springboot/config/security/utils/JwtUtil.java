package com.gly.springboot.config.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gly.springboot.config.security.entity.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author EugeneGe
 * @date 2022-08-24 8:28
 */
@Component
public class JwtUtil {

    private final static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String loginAccount, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("loginAccount", loginAccount)
                    .build();
            // 效验TOKEN
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            logger.info("token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getLoginAccount(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginAccount").asString();
        } catch (JWTDecodeException e) {
            logger.error("error：{}", e);
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param loginAccount 账号
     * @param secret       用户的密码
     * @return token
     */
    public static String sign(String loginAccount, String secret) {
        loginAccount = StringUtils.lowerCase(loginAccount);
        Date date = new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("loginAccount", loginAccount)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
