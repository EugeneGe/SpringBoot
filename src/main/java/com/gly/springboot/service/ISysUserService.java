package com.gly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gly.springboot.entity.sys.SysUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-17
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser selectByLoginAccount(String loginAccount);

    /**
     * 根据条件查询表SysUser信息
     *
     * @param map
     */
    List<Map<String, Object>> selectListByMap(Map<String, Object> map);

}
