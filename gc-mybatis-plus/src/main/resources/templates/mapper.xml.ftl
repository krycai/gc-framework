<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <#assign entityClass></#assign>
    <#if cfg.createSelectVo>
        <#assign entityClass>${cfg.voPackage}.${entity}Vo</#assign>
    <#else>
        <#assign entityClass>${package.Entity}.${entity}</#assign>
    </#if>
    <resultMap id="BaseResultMap" type="${entityClass}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag && field.name!='del_flag'><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
    </resultMap>

</#if>
<#assign hasDelFlag>false</#assign>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <#assign str2></#assign>
    <#list table.fields as field><#if field.name!='del_flag'><#assign str2>${str2}t.${field.name},</#assign><#else><#assign hasDelFlag>true</#assign></#if></#list>
    <sql id="Base_Column_List">
        ${str2?substring(0,str2?length-1)}
    </sql>
</#if>

<#if cfg.createSelectVo>
    <!--通用vo查询-->
    <select id="selectVo" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"></include>
        from ${table.name} t
        <where><#if hasDelFlag == "true">
            t.del_flag = 0</#if>
        </where>
    </select>
</#if>

</mapper>
