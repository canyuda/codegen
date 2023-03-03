<#assign at="@">
<#assign pound="#">
<#assign dollar="$">
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage}.${mapperName}">
    <resultMap id="BaseResultMap" type="${entityPackage}.${entityName}">
        <!--${at}Table ${tableName}-->
        <id column="id" jdbcType="BIGINT" property="id"/>
        <#list columnsList as col>
            <#if col.columnName!='id'>
        <result column="${col.srcColumnName}" jdbcType="${col.jdbcDataType}" property="${col.columnName}"/>
            </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_List">
        <#list columnsList as col>`${col.srcColumnName}`<#sep>, </#list>
    </sql>
</mapper>