package com.gly.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gly.springboot.entity.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-17
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据账号查询用户
     *
     * @param loginAccount
     * @return
     */
    SysUser selectByLoginAccount(@Param("loginAccount") String loginAccount);

    /**
     * 根据条件查询表SysUser信息
     *
     * @param map
     */
    List<Map<String, Object>> selectListByMap(Map<String, Object> map);

}
