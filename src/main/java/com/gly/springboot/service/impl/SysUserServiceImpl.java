package com.gly.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gly.springboot.entity.sys.SysUser;
import com.gly.springboot.mapper.SysUserMapper;
import com.gly.springboot.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser selectByLoginAccount(String loginAccount) {
        return this.baseMapper.selectByLoginAccount(loginAccount);
    }

    @Override
    public List<Map<String, Object>> selectListByMap(Map<String, Object> map) {
        List<Map<String, Object>> list = this.baseMapper.selectListByMap(map);
        return list;
    }
}
