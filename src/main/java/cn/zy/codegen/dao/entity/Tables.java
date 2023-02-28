package cn.zy.codegen.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName(value = "information_schema.`TABLES`")
public class Tables implements Serializable {
    public static final String COL_TABLE_NAME = "TABLE_NAME";
    public static final String COL_TABLE_COMMENT = "TABLE_COMMENT";
    private static final long serialVersionUID = 1L;
    @TableField(value = "`TABLE_NAME`")
    private String tableName;
    @TableField(value = "TABLE_COMMENT")
    private String tableComment;
}