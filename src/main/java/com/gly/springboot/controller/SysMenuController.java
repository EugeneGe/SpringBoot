package com.gly.springboot.controller;

import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.SysMenu;
import com.gly.springboot.service.ISysMenuService;
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
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/base/sysMenu")
@Api(value = "SysMenuController", tags = "SysMenuController控制器")
public class SysMenuController {
    private final static Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private ISysMenuService iSysMenuService;

    /**
     * 菜单权限表新增
     *
     * @param sysMenu SysMenu 对象
     * @return Result
     */
    @ApiOperation(value = "菜单权限表信息", notes = "新增")
    @ResponseBody
    @PostMapping("/add")
    public ResultVo<String> add(@RequestBody SysMenu sysMenu) {
        ResultVo<String> result = new ResultVo<>();
        try {
            if (iSysMenuService.save(sysMenu)) {
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
     * 菜单权限表修改
     *
     * @param sysMenu SysMenu 对象
     * @return Result
     */
    @ApiOperation(value = "菜单权限表信息", notes = "修改")
    @ResponseBody
    @PutMapping("/edit")
    public ResultVo<String> edit(@RequestBody SysMenu sysMenu) {
        ResultVo<String> result = new ResultVo<>();
        try {
            if (iSysMenuService.updateById(sysMenu)) {
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
     * 菜单权限表根据ID查询信息
     *
     * @param id
     * @return Result
     */
    @ApiOperation(value = "菜单权限表信息", notes = "根据ID查询")
    @ResponseBody
    @GetMapping("/selectById")
    public ResultVo<SysMenu> selectById(@RequestParam String id) {
        ResultVo<SysMenu> result = new ResultVo<>();
        try {
            SysMenu sysMenu = iSysMenuService.getById(id);
            result.resultSuccess(sysMenu);
            result.setMsg("查询成功！");
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 菜单权限表查询列表 分页
     *
     * @param map
     * @return Result
     */
    @ApiOperation(value = "菜单权限表信息", notes = "查询列表 分页")
    @ResponseBody
    @GetMapping("/listPage")
    public ResultVo<List<SysMenu>> listPage(HttpServletRequest request,
                                            @RequestBody Map<String, Object> map) {
        ResultVo<List<SysMenu>> result = new ResultVo<>();
        try {
            result = iSysMenuService.listPage(map);
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 菜单权限表查询列表
     *
     * @param map
     * @return Result
     */
    @ApiOperation(value = "菜单权限表信息", notes = "查询列表")
    @ResponseBody
    @GetMapping("/selectListByMap")
    public ResultVo<List<Map<String, Object>>> selectListByMap(HttpServletRequest request,
                                                               @RequestBody Map<String, Object> map) {
        ResultVo<List<Map<String, Object>>> result = new ResultVo<>();
        try {
            result = iSysMenuService.selectListByMap(map);
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}

