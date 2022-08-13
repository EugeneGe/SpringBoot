package com.gly.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gly.springboot.entity.sys.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * <p>
 * web日志记录表 Mapper 接口
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-13
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 根据主键查询表SysLog信息
     *
     * @param id
     */
    Map<String, Object> selectMapById(@Param("id") String id);

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
