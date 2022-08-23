package com.gly.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gly.springboot.config.security.TokenService;
import com.gly.springboot.config.security.utils.SecurityUtils;
import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.Login;
import com.gly.springboot.entity.sys.LoginUser;
import com.gly.springboot.entity.sys.SysUser;
import com.gly.springboot.service.ISysUserService;
import com.gly.springboot.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-17
 */
@RestController
@RequestMapping("/base/sysUser")
@Api(value = "SysUserController", tags = "SysUserController控制器")
public class SysUserController {
    private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResultVo<Map<String, Object>> login(@RequestBody Login login) {
        ResultVo<Map<String, Object>> resultVo = new ResultVo<>();
        //TODO 校验 用户登录
        Wrapper<SysUser> wrapper = new QueryWrapper<>();
        SysUser sysUser = iSysUserService.selectByLoginAccount(login.getLoginAccount());
        // 角色集合
        // 权限集合
        LoginUser loginUser = new LoginUser();
        loginUser.setSysUser(sysUser);
//        sysUserVo.setRoles(roles);
//        sysUserVo.setPermissions(permissions);

        if (!SecurityUtils.matchesPassword(login.getPassword(), sysUser.getPassword())) {
            resultVo.resultFail("密码不匹配！");
        }

        // token
        Map<String, Object> map = tokenService.createToken(loginUser);
        resultVo.resultSuccess(map);
        return resultVo;
    }

    @DeleteMapping("logout")
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

    /**
     * 用户信息表新增
     *
     * @param sysUser SysUser 对象
     * @return Result
     */
    @ApiOperation(value = "用户信息表信息", notes = "新增")
    @ResponseBody
    @PostMapping("/add")
    public ResultVo<String> add(@RequestBody SysUser sysUser) {
        ResultVo<String> resultVo = new ResultVo<>();
        try {
            sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
            sysUser.setLastLoginDate(DateUtil.today());
            sysUser.setCreateTime(DateUtil.today());
            sysUser.setUpdateTime(DateUtil.today());
            if (iSysUserService.save(sysUser)) {
                resultVo.resultSuccess("新增成功！");
                return resultVo;
            } else {
                resultVo.resultSuccess("新增失败！");
                return resultVo;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("新增异常！");
        }
        return resultVo;
    }

    /**
     * 用户信息表修改
     *
     * @param sysUser SysUser 对象
     * @return Result
     */
    @ApiOperation(value = "用户信息表信息", notes = "修改")
    @ResponseBody
    @PutMapping("/update")
    public ResultVo<String> update(@RequestBody SysUser sysUser) {
        ResultVo<String> resultVo = new ResultVo<>();
        try {
            if (iSysUserService.updateById(sysUser)) {
                resultVo.resultSuccess("修改成功！");
                return resultVo;
            } else {
                resultVo.resultSuccess("修改失败！");
                return resultVo;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("修改异常！");
        }
        return resultVo;
    }

    /**
     * 用户信息表根据ID查询信息
     *
     * @param id
     * @return Result
     */
    @ApiOperation(value = "用户信息表信息", notes = "根据ID查询")
    @ResponseBody
    @GetMapping("/selectById")
    public ResultVo<SysUser> selectById(@RequestParam String id) {
        ResultVo<SysUser> resultVo = new ResultVo<>();
        try {
            SysUser sysUser = iSysUserService.getById(id);
            resultVo.resultSuccess(sysUser);
            return resultVo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("修改异常！");
        }
        return resultVo;
    }

    /**
     * 用户信息表查询列表
     *
     * @param map
     * @return Result
     */
    @ApiOperation(value = "用户信息表信息", notes = "查询列表")
    @ResponseBody
    @GetMapping("/list")
    public ResultVo<List<Map<String, Object>>> list(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> map) {
        ResultVo<List<Map<String, Object>>> resultVo = new ResultVo<>();
        try {
            List<Map<String, Object>> list = iSysUserService.selectListByMap(map);
            resultVo.resultSuccess(list);
            return resultVo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }
}

