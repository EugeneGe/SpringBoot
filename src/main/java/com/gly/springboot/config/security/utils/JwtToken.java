package com.gly.springboot.config.security.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author EugeneGe
 * @date 2022-08-24 8:31
 */
@Data
@AllArgsConstructor
public class JwtToken extends UsernamePasswordToken {
    private String token;

    private String exipreAt;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
