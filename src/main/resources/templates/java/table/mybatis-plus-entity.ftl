package ${entityPackage};

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

<#assign at="@">
${at}Data
${at}TableName("${tableName}")
${at}ApiModel("${tableComment!''}")
public class ${entityName} implements Serializable{
    private static final long serialVersionUID = 1L;
    
	<#list columnsList as col>
	<#if col.columnName='id'>
    ${at}TableId(value = "id", type = IdType.AUTO)
	${at}ApiModelProperty("${col.columnComment!'主键'}")
    private Long id;

	<#elseif col.columnName='addTime'>
	${at}TableField(value="add_time",fill=FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
	${at}ApiModelProperty("${col.columnComment!'插入时间'}")
    private LocalDateTime addTime;

	<#elseif col.columnName='updateTime'>
	${at}TableField(value="update_time",fill=FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
	${at}ApiModelProperty("${col.columnComment!'最后更新时间'}")
    private LocalDateTime  updateTime;

	<#elseif col.columnName='deleted'>
	${at}TableField(value="deleted")
	${at}ApiModelProperty("${col.columnComment!'删除标记'}")
	${at}TableLogic()
    private Boolean deleted = false;

	<#else>
	${at}TableField(value="${col.srcColumnName}")
	<#if col.columnType='LocalDateTime'>
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
	<#elseif col.columnType='LocalDate'>
	@JsonFormat(pattern = "yyyy-MM-dd")
	<#elseif col.columnType='LocalTime'>
	@JsonFormat(pattern = "HH:mm:ss")
	</#if>
	${at}ApiModelProperty("${col.columnComment!''}")
    private ${col.columnType} ${col.columnName};
	</#if>
	</#list>

	/**
	* 列名
	*/
	<#list columnsList as col>
	public static final String COL_${col.srcColumnName?upper_case} = "${col.srcColumnName}";
	</#list>
}
