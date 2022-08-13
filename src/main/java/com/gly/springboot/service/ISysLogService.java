package com.gly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gly.springboot.entity.sys.SysLog;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * web日志记录表 服务类
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-13
 */
public interface ISysLogService extends IService<SysLog> {

    /**
     * 根据主键查询表SysLog信息
     *
     * @param id
     */
    Map<String, Object> selectMapById(String id);

    /**
     * 根据条件查询表SysLog信息
     *
     * @param map
     */
    List<Map<String, Object>> selectListByMap(Map<String, Object> map);

    /**
     * 根据条件查询表SysLog信息,查重
     *
     * @param map
     */
    List<Map<String, Object>> checkRepetition(Map<String, Object> map);

}
