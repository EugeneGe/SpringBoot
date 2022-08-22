package com.gly.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gly.springboot.entity.sys.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
    * 根据主键查询表SysRoleMenu信息
    *
    * @param id
    */
    Map<String, Object> selectMapById(@Param("id") String id);


    /**
    * 根据条件查询表SysRoleMenu信息 分页
    *
    * @param map
    */
    List<SysRoleMenu> listPage(Map<String, Object> map);

    /**
    * 根据条件查询表SysRoleMenu信息
    *
    * @param map
    */
    List<Map<String, Object>> selectListByMap(Map<String, Object> map);

    /**
    * 根据条件查询表SysRoleMenu信息,查重
    *
    * @param map
    */
    List<Map<String, Object>> checkRepetition(Map<String, Object> map);

    /**
    * 批量修改
    *
    * @param list
    * @return int
    */
    int updateSysRoleMenuBatch(@Param("list") List<SysRoleMenu> list);

}
