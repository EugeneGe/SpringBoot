package com.gly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.SysRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
    * 根据主键查询表SysRoleMenu信息
    *
    * @param id
    */
    Map<String, Object> selectMapById(String id);

    /**
    * 根据条件查询表SysRoleMenu信息 分页
    *
    * @param map
    */
    ResultVo<List<SysRoleMenu>> listPage(Map<String, Object> map);

    /**
    * 根据条件查询表SysRoleMenu信息
    *
    * @param map
    */
   ResultVo<List<Map<String, Object>>> selectListByMap(Map<String, Object> map);

    /**
    * 根据条件查询表SysRoleMenu信息,查重
    *
    * @param map
    */
   List<Map<String, Object>> checkRepetition(Map<String, Object> map);

}