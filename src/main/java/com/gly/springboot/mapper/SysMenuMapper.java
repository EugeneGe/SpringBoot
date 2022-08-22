package com.gly.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gly.springboot.entity.sys.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 根据主键查询表SysMenu信息
     *
     * @param id
     */
    Map<String, Object> selectMapById(@Param("id") String id);


    /**
     * 根据条件查询表SysMenu信息 分页
     *
     * @param map
     */
    List<SysMenu> listPage(Map<String, Object> map);

    /**
     * 根据条件查询表SysMenu信息
     *
     * @param map
     */
    List<Map<String, Object>> selectListByMap(Map<String, Object> map);

    /**
     * 根据条件查询表SysMenu信息,查重
     *
     * @param map
     */
    List<Map<String, Object>> checkRepetition(Map<String, Object> map);
}
