package com.gly.springboot.entity.sys;

/**
 * @author EugeneGe
 * @date 2022-08-17 20:34
 */
public class Login {

    /**
     * 账号
     */
    private String loginAccount;

    /**
     * 用户密码
     */
    private String password;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
