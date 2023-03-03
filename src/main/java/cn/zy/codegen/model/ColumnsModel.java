package cn.zy.codegen.model;

import lombok.Data;

@Data
public class ColumnsModel {

    private String tableSchema;

    private String tableName;

    private String columnName;

    private String srcColumnName;

    private Long ordinalPosition;

    private String columnDefault;

    private String isNullable;

    private String dataType;

    private String jdbcDataType;

    private Long characterMaximumLength;

    private Long characterOctetLength;

    private Long numericPrecision;

    private Long numericScale;

    private String columnType;

    private String columnKey;

    private String extra;

    private String columnComment;
}
