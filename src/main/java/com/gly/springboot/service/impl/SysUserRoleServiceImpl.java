package com.gly.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gly.springboot.entity.common.ResultVo;
import com.gly.springboot.entity.sys.SysUserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gly.springboot.mapper.SysUserRoleMapper;
import com.gly.springboot.service.ISysUserRoleService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public Map<String, Object> selectMapById(String id) {
        return this.baseMapper.selectMapById(id);
    }

    @Override
    public ResultVo<List<SysUserRole>> listPage(Map<String, Object> map) {
        ResultVo<List<SysUserRole>> resultVo = new ResultVo<>();
        PageHelper.startPage(MapUtils.getInteger(map, "currentPage"), MapUtils.getInteger(map, "pageSize"));
        List<SysUserRole> list = this.baseMapper.listPage(map);

        PageInfo<SysUserRole> pageInfo = new PageInfo<>(list);
        resultVo.resultSuccess(list);
        long total = pageInfo.getTotal();
        resultVo.setTotal((int) (total));
        return resultVo;
    }

    @Override
    public ResultVo<List<Map<String, Object>>> selectListByMap(Map<String, Object> map) {
        ResultVo<List<Map<String, Object>>> resultVo = new ResultVo<>();
        List<Map<String, Object>> list = this.baseMapper.selectListByMap(map);
        resultVo.resultSuccess(list);
        return resultVo;
    }

    @Override
    public List<Map<String, Object>> checkRepetition(Map<String, Object> map) {
        return this.baseMapper.checkRepetition(map);
    }

}
