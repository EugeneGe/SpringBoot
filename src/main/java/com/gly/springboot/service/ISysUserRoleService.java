package com.gly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.SysUserRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据主键查询表SysUserRole信息
     *
     * @param id
     */
    Map<String, Object> selectMapById(String id);

    /**
     * 根据条件查询表SysUserRole信息 分页
     *
     * @param map
     */
    ResultVo<List<SysUserRole>> listPage(Map<String, Object> map);

    /**
     * 根据条件查询表SysUserRole信息
     *
     * @param map
     */
    ResultVo<List<Map<String, Object>>> selectListByMap(Map<String, Object> map);

    /**
     * 根据条件查询表SysUserRole信息,查重
     *
     * @param map
     */
    List<Map<String, Object>> checkRepetition(Map<String, Object> map);

}
