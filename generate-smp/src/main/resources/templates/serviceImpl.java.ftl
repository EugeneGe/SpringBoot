package ${package.ServiceImpl};

<#--import ${package.Entity}.${entity};-->
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.MapUtils;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
* <p>
    * ${table.comment!} 服务实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public Map<String, Object> selectMapById(String id){
    return this.baseMapper.selectMapById(id);
    }

<#--@Override
public ResultVo<List<Map<String, Object>>> selectListByMap(Map<String,Object> map){
    ResultVo<List<Map<String, Object>>> resultVo = new ResultVo<>();
    PageHelper.startPage(MapUtils.getInteger(map, "currentPage"), MapUtils.getInteger(map, "pageSize"));
    List<Map<String, Object>> list = this.baseMapper.selectListByMap(map);

    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
    resultVo.resultSuccess(list);
    long total = pageInfo.getTotal();
    resultVo.setTotal((int) (total));
    return resultVo;
}-->
    @Override
    public List<Map<String, Object>> selectListByMap(Map<String,Object> map){
    List<Map<String, Object>> list = this.baseMapper.selectListByMap(map);
    return list;
    }

    @Override
    public List<Map<String, Object>> checkRepetition(Map<String,Object> map){
    return this.baseMapper.checkRepetition(map);
    }

<#--@Override
public int update${entity}Batch(List<Map<String, Object>> list){
    return this.baseMapper.update${entity}Batch(list);
}-->

    }
</#if>
