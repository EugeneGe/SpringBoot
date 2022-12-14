package ${package.Service};

<#--import ${package.Entity}.${entity};-->
import ${superServiceClassPackage};
import java.util.List;
import java.util.Map;

/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 根据主键查询表${entity}信息
    *
    * @param id
    */
    Map<String, Object> selectMapById(String id);

    /**
    * 根据条件查询表${entity}信息
    *
    * @param map
    */
<#--   ResultVo<List<Map<String,Object>>> selectListByMap(Map<String,Object> map);-->
    List<Map<String,Object>> selectListByMap(Map<String,Object> map);

    /**
    * 根据条件查询表${entity}信息,查重
    *
    * @param map
    */
    List<Map<String, Object>> checkRepetition(Map<String, Object> map);

<#--/**
* 批量修改
*
* @param list
* @return int
*/
int update${entity}Batch(List<Map<String, Object>> list);-->
    }
</#if>
