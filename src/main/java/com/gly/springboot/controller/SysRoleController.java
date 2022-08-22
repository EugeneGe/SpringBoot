package com.gly.springboot.controller;

import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.SysRole;
import com.gly.springboot.service.ISysRoleService;
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
 * 角色信息表 前端控制器
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/base/sysRole")
@Api(value = "SysRoleController", tags = "SysRoleController控制器")
public class SysRoleController {
    private final static Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 角色信息表新增
     *
     * @param sysRole SysRole 对象
     * @return Result
     */
    @ApiOperation(value = "角色信息表信息", notes = "新增")
    @ResponseBody
    @PostMapping("/add")
    public ResultVo<String> add(@RequestBody SysRole sysRole) {
        ResultVo<String> result = new ResultVo<>();
        try {
            if (iSysRoleService.save(sysRole)) {
                result.resultSuccess("新增成功!");
            } else {
                result.resultFail("新增失败!");
            }
        } catch (Exception e) {
            result.resultFail("新增异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 角色信息表修改
     *
     * @param sysRole SysRole 对象
     * @return Result
     */
    @ApiOperation(value = "角色信息表信息", notes = "修改")
    @ResponseBody
    @PutMapping("/edit")
    public ResultVo<String> edit(@RequestBody SysRole sysRole) {
        ResultVo<String> result = new ResultVo<>();
        try {
            if (iSysRoleService.updateById(sysRole)) {
                result.resultSuccess("修改成功!");
            } else {
                result.resultFail("修改失败!");
            }
        } catch (Exception e) {
            result.resultFail("修改异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 角色信息表根据ID查询信息
     *
     * @param id
     * @return Result
     */
    @ApiOperation(value = "角色信息表信息", notes = "根据ID查询")
    @ResponseBody
    @GetMapping("/selectById")
    public ResultVo<SysRole> selectById(@RequestParam String id) {
        ResultVo<SysRole> result = new ResultVo<>();
        try {
            SysRole sysRole = iSysRoleService.getById(id);
            result.resultSuccess(sysRole);
            result.setMsg("查询成功！");
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 角色信息表查询列表 分页
     *
     * @param map
     * @return Result
     */
    @ApiOperation(value = "角色信息表信息", notes = "查询列表 分页")
    @ResponseBody
    @PostMapping("/listPage")
    public ResultVo<List<SysRole>> listPage(HttpServletRequest request,
                                            @RequestBody Map<String, Object> map) {
        ResultVo<List<SysRole>> result = new ResultVo<>();
        try {
            result = iSysRoleService.listPage(map);
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 角色信息表查询列表
     *
     * @param map
     * @return Result
     */
    @ApiOperation(value = "角色信息表信息", notes = "查询列表")
    @ResponseBody
    @PostMapping("/selectListByMap")
    public ResultVo<List<Map<String, Object>>> selectListByMap(HttpServletRequest request,
                                                               @RequestBody Map<String, Object> map) {
        ResultVo<List<Map<String, Object>>> result = new ResultVo<>();
        try {
            result = iSysRoleService.selectListByMap(map);
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}

