package com.yicheng.common.redis.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author smartever
 */
@Component
public class RecalibrationService {

    @Autowired
    private RedisService redisService;

    /**
     * 缓存有效期，默认5（分钟）
     */
    private final static long expireTime = 5;

    /**
     * 保存key前缀
     */
    private final static String ACCESS_TOKEN = "order_use:";

    /**
     * 获取数据缓存信息
     */
    public String getDataInfo(String data) {
        if (StringUtils.isNotEmpty(data)) {
            String dataKey = getUseKey(data);
            return redisService.getCacheObject(dataKey);
        }
        return null;
    }

    /**
     * 设置数据信息
     */
    public void setDataInfo(String data) {
        if (StringUtils.isNotEmpty(data)) {
            // 根据uuid将loginUser缓存
            String dataKey = getUseKey(data);
            redisService.setCacheObject(dataKey, data, expireTime, TimeUnit.MINUTES);
        }
    }

    /**
     * 删除数据缓存信息
     */
    public void delDataInfo(String data) {
        if (StringUtils.isNotEmpty(data)) {
            String dataKey = getUseKey(data);
            redisService.deleteObject(dataKey);
        }
    }

    private String getUseKey(String data) {
        return ACCESS_TOKEN + data;
    }
}
