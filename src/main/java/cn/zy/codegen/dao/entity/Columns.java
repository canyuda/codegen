package cn.zy.codegen.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "information_schema.`COLUMNS`")
public class Columns implements Serializable {
    public static final String COL_COLUMN_NAME = "COLUMN_NAME";
    public static final String COL_ORDINAL_POSITION = "ORDINAL_POSITION";
    public static final String COL_COLUMN_DEFAULT = "COLUMN_DEFAULT";
    public static final String COL_IS_NULLABLE = "IS_NULLABLE";
    public static final String COL_DATA_TYPE = "DATA_TYPE";
    public static final String COL_CHARACTER_MAXIMUM_LENGTH = "CHARACTER_MAXIMUM_LENGTH";
    public static final String COL_CHARACTER_OCTET_LENGTH = "CHARACTER_OCTET_LENGTH";
    public static final String COL_NUMERIC_PRECISION = "NUMERIC_PRECISION";
    public static final String COL_NUMERIC_SCALE = "NUMERIC_SCALE";
    public static final String COL_COLUMN_TYPE = "COLUMN_TYPE";
    public static final String COL_COLUMN_KEY = "COLUMN_KEY";
    public static final String COL_EXTRA = "EXTRA";
    public static final String COL_COLUMN_COMMENT = "COLUMN_COMMENT";
    private static final long serialVersionUID = 1L;
    @TableField(value = "`COLUMN_NAME`")
    private String columnName;
    @TableField(value = "ORDINAL_POSITION")
    private Long ordinalPosition;
    @TableField(value = "COLUMN_DEFAULT")
    private String columnDefault;
    @TableField(value = "IS_NULLABLE")
    private String isNullable;
    @TableField(value = "DATA_TYPE")
    private String dataType;
    @TableField(value = "CHARACTER_MAXIMUM_LENGTH")
    private Long characterMaximumLength;
    @TableField(value = "CHARACTER_OCTET_LENGTH")
    private Long characterOctetLength;
    @TableField(value = "NUMERIC_PRECISION")
    private Long numericPrecision;
    @TableField(value = "NUMERIC_SCALE")
    private Long numericScale;
    @TableField(value = "COLUMN_TYPE")
    private String columnType;
    @TableField(value = "COLUMN_KEY")
    private String columnKey;
    @TableField(value = "EXTRA")
    private String extra;
    @TableField(value = "COLUMN_COMMENT")
    private String columnComment;
}