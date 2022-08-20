package com.gly.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.SysLog;
import com.gly.springboot.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * web日志记录表 前端控制器
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-13
 */
@RestController
@RequestMapping("/base/sysLog")
@Api(value = "SysLogController", tags = "SysLogController控制器")
public class SysLogController {
    private final static Logger logger = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private ISysLogService iSysLogService;

    /**
     * 新增
     *
     * @param sysLog
     * @return
     */
    @ApiOperation(value = "add", notes = "新增")
    @ResponseBody
    @PostMapping("/add")
    public ResultVo<String> add(@RequestBody SysLog sysLog) {
        ResultVo<String> resultVo = new ResultVo<>();
        try {
            if (iSysLogService.save(sysLog)) {
                resultVo.resultSuccess("新增成功！");
            } else {
                resultVo.resultSuccess("新增失败！");
            }
            return resultVo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("新增异常!");
        }
        return resultVo;
    }

    /**
     * 修改
     *
     * @param sysLog
     * @return
     */
    @ApiOperation(value = "edit", notes = "修改")
    @ResponseBody
    @PutMapping("/edit")
    public ResultVo<String> edit(@RequestBody SysLog sysLog) {
        ResultVo<String> resultVo = new ResultVo<>();
        try {
            if (iSysLogService.updateById(sysLog)) {
                resultVo.resultSuccess("修改成功！");
            } else {
                resultVo.resultSuccess("修改失败！");
            }
            return resultVo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("修改异常!");
        }
        return resultVo;
    }

    /**
     * web日志记录表根据ID查询信息
     *
     * @param id
     * @return Result
     */
    @ApiOperation(value = "web日志记录表信息", notes = "根据ID查询")
    @ResponseBody
    @GetMapping("/selectById")
    public ResultVo<Map<String, Object>> selectById(@RequestParam String id) {
        ResultVo<Map<String, Object>> resultVo = new ResultVo<>();
        try {
            Map<String, Object> sysLogMap = iSysLogService.selectMapById(id);
            resultVo.setMsg("查询成功");
            resultVo.resultSuccess(sysLogMap);
            return resultVo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("查询异常!");
        }
        return resultVo;
    }

    /**
     * 查询 分页
     *
     * @param paramMap
     * @return
     */
    @ApiOperation(value = "列表查询 分页", notes = "根据map查询")
    @ResponseBody
    @GetMapping("/selectList")
    public ResultVo<List<SysLog>> selectList(@RequestBody(required = false) Map<String, Object> paramMap) {
        ResultVo<List<SysLog>> resultVo = null;
        try {
            resultVo = iSysLogService.selectList(paramMap);
            return resultVo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("查询异常!");
        }
        return resultVo;
    }
}

