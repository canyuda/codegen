package cn.zy.codegen.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "information_schema.`TABLES`")
public class Tables implements Serializable {
    @TableField(value = "TABLE_SCHEMA")
    private String tableSchema;

    @TableField(value = "`TABLE_NAME`")
    private String tableName;

    @TableField(value = "TABLE_COMMENT")
    private String tableComment;

    private static final long serialVersionUID = 1L;

    public static final String COL_TABLE_SCHEMA = "TABLE_SCHEMA";

    public static final String COL_TABLE_NAME = "TABLE_NAME";

    public static final String COL_TABLE_COMMENT = "TABLE_COMMENT";
}