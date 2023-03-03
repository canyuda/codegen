package cn.zy.codegen.model;

import cn.hutool.core.util.StrUtil;
import cn.zy.codegen.config.ProjectProperties;
import cn.zy.codegen.dao.entity.Tables;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TableDefModel {

    public TableDefModel() {
    }

    public TableDefModel(Tables tables, ProjectProperties projectProperties) {
        this.tableName = "`" + tables.getTableSchema() + "`." + tables.getTableName();
        this.tableComment = tables.getTableComment();
        this.upperTableName = StrUtil.upperFirst(StrUtil.toCamelCase(tables.getTableName()));
        this.entityName = upperTableName + "Entity";
        this.voName = upperTableName + "VO";
        this.converterName = upperTableName + "Converter";
        this.serviceImplName = upperTableName + "ServiceImpl";
        this.serviceName = upperTableName + "Service";
        this.mapperName = upperTableName + "Mapper";
        this.controllerName = upperTableName + "Controller";
        this.extName = upperTableName + "Ext";
        this.entityPackage = projectProperties.getBasePackage() + "." + projectProperties.getAppProjectNameToCamelCase() + ".dao.entity";
        this.mapperPackage = projectProperties.getBasePackage() + "." + projectProperties.getAppProjectNameToCamelCase() + ".dao.mapper";
        this.voPackage = projectProperties.getBasePackage() + "." + projectProperties.getAppProjectNameToCamelCase() + ".common.vo";
        this.converterPackage = projectProperties.getBasePackage() + "." + projectProperties.getAppProjectNameToCamelCase() + ".service.converter";
        this.servicePackage = projectProperties.getBasePackage() + "." + projectProperties.getAppProjectNameToCamelCase() + ".service.service";
        this.serviceImplPackage = projectProperties.getBasePackage() + "." + projectProperties.getAppProjectNameToCamelCase() + ".service.service.impl";
    }

    private String tableName;
    private String tableComment;
    private String upperTableName;
    private String entityName;
    private String entityPackage;
    private String voName;
    private String voPackage;
    private String converterName;
    private String converterPackage;
    private String mapperName;
    private String mapperPackage;
    private String controllerName;
    private String serviceImplName;
    private String serviceImplPackage;
    private String serviceName;
    private String servicePackage;
    private String extName;

    private List<ColumnsModel> columnsList;

    private List<UniqIndexModel> uniqIndexModels;

    private List<TableDefModel> childTables;

    private boolean oneToMany = false;

    private String foreignFieldKey;

    private Set<String> uniqueIndex;
}
