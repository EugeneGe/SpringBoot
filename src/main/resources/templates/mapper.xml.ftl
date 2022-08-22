<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_List">
<#list table.commonFields as field>
        ${field.name},
</#list>
        ${table.fieldNames}
    </sql>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#list table.fields as field><#if field.name == field.propertyName>${field.name}<#else>${field.name} AS ${field.propertyName}</#if><#if field_has_next>,</#if></#list>
    </sql>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List_2">
        <#list table.fields as field>
            ${r"u."}${field.name} AS ${field.propertyName},
        </#list>
    </sql>

    <!-- 通用条件列 -->
    <sql id="${entity}ByCondition">
        <#list table.commonFields as field><#--生成公共字段-->
        <if test="${field.propertyName} != null and ${field.propertyName} != ''">
            AND ${field.name} = ${r"#{"}${field.propertyName}${r"}"}
        </if>
        </#list>
        <#list table.fields as field>
            <#if !field.keyFlag><#--生成普通字段 -->
                <#if field.type == "datetime" || field.type == "date">
        <if test="${field.propertyName} != null and ${field.propertyName} != ''">
            AND ${field.name} <![CDATA[ = ]]>  ${r"#{"}${field.propertyName}${r"}"}
        </if>
                <#else>
        <if test="${field.propertyName} != null and ${field.propertyName} != ''">
            AND ${field.name} = ${r"#{"}${field.propertyName}${r"}"}
        </if>
                </#if>
            </#if>
        </#list>
        <if test="list != null and list.size() > 0">
            AND id IN
            <foreach collection="list" separator="," item="item" index="index" open="(" close=")">
                ${r"#{"}item.id${r"}"}
            </foreach>
        </if>
    </sql>

    <!-- 根据主键查询表${table.name}信息 -->
    <select id="selectMapById" resultType="java.util.Map">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
        WHERE
        <#list table.fields as field>
            <#if field.keyFlag>
            ${field.name} = ${r"#{"}id${r"}"}
            </#if>
        </#list>
    </select>

    <!-- 根据条件查询表${table.name}信息 -->
    <select id="listPage" resultType="${package.Entity}.${entity}">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
        <where>
            <include refid="${entity}ByCondition" />
        </where>
    </select>
    <#--<!-- 根据条件查询表${table.name}信息,pagehelper count方法重写 &ndash;&gt;
    <select id="listPage_COUNT" resultType="java.lang.Long">
    SELECT
    count(1)
    FROM ${table.name}
    <where>
    <include refid="${entity}ByCondition" />
    </where>
    </select>-->

    <!-- 根据条件查询表${table.name}信息 -->
    <select id="selectListByMap" resultType="java.util.Map">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
    <where>
        <include refid="${entity}ByCondition" />
    </where>
    </select>

    <!--检验重复性-->
    <select id="checkRepetition" parameterType="java.util.Map" resultType="java.util.Map">
        SELECT
        id
        FROM ${table.name}
    <where>
        <if test="id != null and id != ''">
            AND id != ${r"#{"}id${r"}"}
        </if>
        <include refid="${entity}ByCondition" />
    </where>
    </select>

    <#--&lt;#&ndash;批量修改&ndash;&gt;
    <update id="update${entity}Batch">
        UPDATE ${table.name} SET
        <#list table.commonFields as field>&lt;#&ndash;生成公共字段 &ndash;&gt;
        ${field.name}=
        <foreach collection="list" separator=" " item="item" index="index" open="case " close="end">
            WHEN id=${r"#{"}item.id${r"}"}  THEN ${r"#{item."}${field.propertyName}${r"}"}
        </foreach>
        <#if field_has_next>,</#if>
        </#list>
        <#list table.fields as field>&lt;#&ndash;生成普通字段 &ndash;&gt;
            <#if !field.keyFlag>
        ${field.name}=
        <foreach collection="list" separator=" " item="item" index="index" open="case " close="end">
            WHEN id=${r"#{"}item.id${r"}"}  THEN ${r"#{item."}${field.propertyName}${r"}"}
        </foreach>
        <#if field_has_next>,</#if>
            </#if>
        </#list>
        WHERE id =
        <foreach collection="list" separator="," item="item" index="index" open="(" close=")">
            ${r"#{"}item.id${r"}"}
        </foreach>
    </update>-->

    <!--批量修改-->
    <update id="update${entity}Batch">
        UPDATE ${table.name} SET
        <#list table.commonFields as field><#--生成公共字段 -->
        ${field.name}=
        <foreach collection="list" separator=" " item="item" index="index" open="case " close="end">
            WHEN id=${r"#{"}item.id${r"}"} and test=${r"#{"}item.test${r"}"}  THEN ${r"#{item."}${field.propertyName}${r"}"}
        </foreach>
        <#if field_has_next>,</#if>
        </#list>
        <#list table.fields as field><#--生成普通字段 -->
            <#if !field.keyFlag>
        ${field.name}=
        <foreach collection="list" separator=" " item="item" index="index" open="case " close="end">
            WHEN id=${r"#{"}item.id${r"}"} and test=${r"#{"}item.test${r"}"}  THEN ${r"#{item."}${field.propertyName}${r"}"}
        </foreach>
        <#if field_has_next>,</#if>
            </#if>
        </#list>
        WHERE (id,test) in
        <foreach collection="list" separator="," item="item" index="index" open="(" close=")">
            (${r"#{"}item.id${r"}"},${r"#{"}item.id${r"}"})
        </foreach>
    </update>

</#if>

</mapper>
