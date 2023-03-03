package cn.zy.codegen.converter;

public class TypeConverter {
    public static String getTypeName(String dataType) {
        switch (dataType) {
            case "double":
                return "Double";
            case "bigint":
                return "Long";
            case "tinyint":
                return "Boolean";
            case "smallint":
            case "int":
                return "Integer";
            case "datetime":
            case "timestamp":
                return "LocalDateTime";
            case "date":
                return "LocalDate";
            case "time":
                return "LocalTime";
            case "char":
            case "varchar":
            default:
                return "String";
        }
    }

    public static String getJdbcDateType(String dataType) {
        switch (dataType) {
            case "double":
                return "DOUBLE";
            case "bigint":
                return "BIGINT";
            case "tinyint":
                return "BOOLEAN";
            case "smallint":
            case "int":
                return "INTEGER";
            case "datetime":
            case "timestamp":
                return "TIMESTAMP";
            case "date":
                return "DATE";
            case "time":
                return "TIME";
            case "decimal":
                return "DECIMAL";
            case "char":
            case "varchar":
            default:
                return "VARCHAR";
        }
    }
}
