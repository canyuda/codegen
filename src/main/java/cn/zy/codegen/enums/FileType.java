package cn.zy.codegen.enums;

import lombok.Getter;

@Getter
public enum FileType {
    JAVA("java", ".java"),
    XML("xml", ".xml"),
    PROPERTIES("properties", ".properties"),
    ;

    private final String name;
    private final String suffix;

    FileType(String name, String suffix) {
        this.name = name;
        this.suffix = suffix;
    }
}
