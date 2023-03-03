package ${voPackage};

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#assign at="@">
${at}Data
${at}ApiModel("${tableComment!''}")
public class ${voName} implements Serializable{
    private static final long serialVersionUID = 1L;

<#list columnsList as col>
    <#if col.columnName='id'>
        ${at}ApiModelProperty("${col.columnComment!'主键'}")
        private Long id;
    <#elseif col.columnName='addTime'><#elseif col.columnName='updateTime'><#elseif col.columnName='deleted'>
    <#else>
        <#if col.columnType='LocalDateTime'>
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
        </#if>
        ${at}ApiModelProperty("${col.columnComment!''}")
        private ${col.columnType} ${col.columnName};
    </#if>
</#list>
}
