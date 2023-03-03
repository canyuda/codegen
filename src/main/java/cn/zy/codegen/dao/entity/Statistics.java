package cn.zy.codegen.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "information_schema.`STATISTICS`")
public class Statistics implements Serializable {
    @TableField(value = "TABLE_SCHEMA")
    private String tableSchema;

    @TableField(value = "`TABLE_NAME`")
    private String tableName;

    @TableField(value = "NON_UNIQUE")
    private Long nonUnique;

    @TableField(value = "INDEX_NAME")
    private String indexName;

    @TableField(value = "`COLUMN_NAME`")
    private String columnName;

    private static final long serialVersionUID = 1L;

    public static final String COL_TABLE_SCHEMA = "TABLE_SCHEMA";

    public static final String COL_TABLE_NAME = "TABLE_NAME";

    public static final String COL_NON_UNIQUE = "NON_UNIQUE";

    public static final String COL_INDEX_NAME = "INDEX_NAME";

    public static final String COL_COLUMN_NAME = "COLUMN_NAME";
}