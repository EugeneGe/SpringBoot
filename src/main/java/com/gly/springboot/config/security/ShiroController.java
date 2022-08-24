package com.gly.springboot.config.security;

import com.gly.springboot.config.security.entity.SecurityConstants;
import com.gly.springboot.config.security.utils.JwtUtil;
import com.gly.springboot.config.security.utils.SHAUtils;
import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.Login;
import com.gly.springboot.entity.sys.SysUser;
import com.gly.springboot.service.ISysUserService;
import com.gly.springboot.utils.DateUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-17
 */
@RestController
@RequestMapping("/base/shiro")
@Api(value = "ShiroController", tags = "ShiroController控制器")
public class ShiroController {
    private final static Logger logger = LoggerFactory.getLogger(ShiroController.class);

    @Autowired
    private ISysUserService iSysUserService;

    @PostMapping("/login")
    public ResultVo<Map<String, Object>> login(@RequestBody Login login, HttpServletResponse response) {
        ResultVo<Map<String, Object>> resultVo = new ResultVo<>();
        Map<String, Object> map = new HashMap<>();

        String loginAccount = login.getLoginAccount();
        String password = login.getPassword();

        try {
            SysUser sysUser = iSysUserService.selectByLoginAccount(loginAccount);
            if (sysUser == null) {
                resultVo.resultFail("用户名不存在!");
                return resultVo;
            }
            String passwdWith = SHAUtils.encodeSHA256Hex(password);

            if (!StringUtils.equals(sysUser.getPassword(), passwdWith)) {
                resultVo.resultFail("密码错误!");
                return resultVo;
            }
//        userService.updateLoginTime(user);
            String token = JwtUtil.sign(loginAccount, password);
            LocalDateTime expireTime = LocalDateTime.now().plusSeconds(SecurityConstants.REFRESH_TIME);
            String expireTimeStr = DateUtil.formatFullTime(expireTime);
//        JwtToken jwtToken = new JwtToken(token, expireTimeStr);

            map.put(SecurityConstants.AUTHENTICATION, token);
            resultVo.resultSuccess(map);
        } catch (Exception e) {

        }

        return resultVo;
    }

    @DeleteMapping("/logout")
    public ResultVo<?> logout(HttpServletRequest request) {
        ResultVo<?> resultVo = new ResultVo<>();
//        String token = SecurityUtils.getToken(request);
//        if (StringUtils.isNotEmpty(token)) {
//            String username = JwtUtils.getUserName(token);
//            // 删除用户缓存记录
//            AuthUtil.logoutByToken(token);
//            // 记录用户退出日志
//            sysLoginService.logout(username);
//        }
        return resultVo;
    }
}
