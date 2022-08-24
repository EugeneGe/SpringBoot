package com.gly.springboot.config.security.config;

import com.gly.springboot.config.security.utils.JwtToken;
import com.gly.springboot.config.security.utils.JwtUtil;
import com.gly.springboot.entity.sys.SysUser;
import com.gly.springboot.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author EugeneGe
 * @date 2022-08-23 8:43
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    ISysUserService sysUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权 对用户进行角色授权
     *
     * @param principalCollection 用户信息
     * @return 返回用户授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String loginAccount = JwtUtil.getLoginAccount(principalCollection.toString());

        SysUser user = sysUserService.selectByLoginAccount(loginAccount);

//        Set<String> roles = roleService.findRoleByUserId(user.getId());
        Set<String> permissions = new HashSet<>();
//        Set<String> permissions = menuService.findPermsByUserId(user.getId());
        permissions = permissions.stream().filter(s -> s != null && !s.equals("")).collect(Collectors.toSet());
//        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     * 认证
     * Realm的认证方法，自动将token传入，比较token与数据库的数据是否匹配
     * 验证逻辑是先根据用户名查询用户，
     * 如果查询到的话再将查询到的用户名和密码放到SimpleAuthenticationInfo对象中，
     * Shiro会自动根据用户输入的密码和查询到的密码进行匹配，如果匹配不上就会抛出异常，
     * 匹配上之后就会执行doGetAuthorizationInfo()进行相应的权限验证。
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        String loginAccount = JwtUtil.getLoginAccount(token);
        if (StringUtils.isBlank(loginAccount)) {
            throw new AuthenticationException("token校验不通过");
        }

        // 通过用户名查询用户信息
        SysUser user = sysUserService.selectByLoginAccount(loginAccount);
        if (user == null) {
            throw new UnknownAccountException();
        }
//        if (!JwtUtil.verify(token, loginAccount, user.getPassword())) {
//            throw new TokenTimeoutException("token校验不通过");
//        }
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token,
                token,
                getName()
        );
        //角色权限校验

        return authenticationInfo;
    }
}
