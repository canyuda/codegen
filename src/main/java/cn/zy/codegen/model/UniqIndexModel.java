package cn.zy.codegen.model;

import lombok.Data;

import java.util.List;

@Data
public class UniqIndexModel {
    private String methodName;

    private List<String> fields;
}
