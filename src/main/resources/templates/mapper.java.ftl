package ${package.Mapper};

<#--import ${package.Entity}.${entity};-->
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    /**
    * 根据主键查询表${entity}信息
    *
    * @param id
    */
    Map<String, Object> selectMapById(@Param("id") String id);


    /**
    * 根据条件查询表${entity}信息
    *
    * @param map
    */
    List<Map<String, Object>> selectListByMap(Map<String, Object> map);

    /**
    * 根据条件查询表${entity}信息,查重
    *
    * @param map
    */
    List<Map<String, Object>> checkRepetition(Map<String, Object> map);

   <#-- /**
    * 批量修改
    *
    * @param list
    * @return int
    */
    int update${entity}Batch(@Param("list") List<Map<String, Object>> list);-->

}
</#if>
