package com.gly.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gly.springboot.entity.sys.SysLog;
import com.gly.springboot.mapper.SysLogMapper;
import com.gly.springboot.service.ISysLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * web日志记录表 服务实现类
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-13
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public Map<String, Object> selectMapById(String id) {
        return this.baseMapper.selectMapById(id);
    }

    @Override
    public List<Map<String, Object>> selectListByMap(Map<String, Object> map) {
        List<Map<String, Object>> list = this.baseMapper.selectListByMap(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> checkRepetition(Map<String, Object> map) {
        return this.baseMapper.checkRepetition(map);
    }

}
