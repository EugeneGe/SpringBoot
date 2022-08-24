package com.gly.springboot.controller;

import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.redis.RedisDao;
import com.gly.springboot.service.ISysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-13
 */
@RestController
@RequestMapping("/test/redis")
@Api(value = "RedisTestController", tags = "RedisTestController控制器")
public class RedisTestController {
    private final static Logger logger = LoggerFactory.getLogger(RedisTestController.class);

    @Autowired
    private RedisDao redisDao;

    /**
     * 新增key value
     *
     * @param key
     * @param value
     * @return
     */
    @ApiOperation(value = "redis新增", notes = "redis新增")
    @ResponseBody
    @GetMapping("/add")
    public ResultVo<Map<String, Object>> add(@RequestParam String key, @RequestParam String value) {
        ResultVo<Map<String, Object>> resultVo = new ResultVo<>();
        try {
            redisDao.setCacheObject(key, value);
            resultVo.setMsg("新增成功！");
            return resultVo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo.resultFail("新增异常!");
        }
        return resultVo;
    }

}

