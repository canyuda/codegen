package cn.zy.codegen.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.zy.codegen.config.ProjectProperties;
import cn.zy.codegen.config.template.TableTemplateManagerConfig;
import cn.zy.codegen.config.template.TemplateManagerConfig;
import cn.zy.codegen.converter.TypeConverter;
import cn.zy.codegen.dao.entity.Columns;
import cn.zy.codegen.dao.entity.Statistics;
import cn.zy.codegen.dao.entity.Tables;
import cn.zy.codegen.enums.FileType;
import cn.zy.codegen.model.ColumnsModel;
import cn.zy.codegen.model.TableDefModel;
import cn.zy.codegen.model.UniqIndexModel;
import cn.zy.codegen.service.ColumnsService;
import cn.zy.codegen.service.StatisticsService;
import cn.zy.codegen.service.TableScanService;
import cn.zy.codegen.service.TablesService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import freemarker.template.Template;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TableScanServiceImpl implements TableScanService {
    @Autowired
    private Map<String, Template> templateMap;
    @Autowired
    private ColumnsService columnsService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private TablesService tablesService;
    @Autowired
    private ProjectProperties projectProperties;

    @Override
    public Template getTemplate(String name) {
        return templateMap.get(name);
    }

    @Override
    @SneakyThrows
    public void genMainPom(String baseDir) {
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MAIN_POM), projectProperties);
        FileUtil.writeString(result, baseDir + "/pom.xml", StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public void genModuleClientPom(String baseDir) {
        String moduleDir = String.format("%s/%s-client", baseDir, projectProperties.getAppProjectName());
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_CLIENT_POM), projectProperties);
        FileUtil.writeString(result, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public void genModuleCommonPom(String baseDir) {
        String moduleDir = String.format("%s/%s-common", baseDir, projectProperties.getAppProjectName());
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_COMMON_POM), projectProperties);
        FileUtil.writeString(result, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public void genModuleDaoPom(String baseDir) {
        String moduleDir = String.format("%s/%s-dao", baseDir, projectProperties.getAppProjectName());
        // ??????pom??????
        String pomResult = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_DAO_POM), projectProperties);
        FileUtil.writeString(pomResult, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
        // ??????dao?????????????????????
        String metaHandlerPath = String.format("%s/src/main/java/%s/%s/dao/meta/handler/", moduleDir,
                projectProperties.getBasePackagePath(),
                projectProperties.getAppProjectNameToCamelCase());
        String metaHandler = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.META_HANDLER), projectProperties);
        FileUtil.writeString(metaHandler, metaHandlerPath + TemplateManagerConfig.META_HANDLER + ".java", StandardCharsets.UTF_8);
        // ??????test?????????
        FileUtil.mkdir(String.format("%s/src/test/java/%s/dao/", moduleDir, projectProperties.getBasePackagePath()));
    }

    @SneakyThrows
    @Override
    public void genModuleHandlerPom(String baseDir) {
        String moduleDir = String.format("%s/%s-handle", baseDir, projectProperties.getAppProjectName());
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_HANDLER_POM), projectProperties);
        FileUtil.writeString(result, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public void genModuleIntegrationPom(String baseDir) {
        String moduleDir = String.format("%s/%s-integration", baseDir, projectProperties.getAppProjectName());
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_INTEGRATION_POM), projectProperties);
        FileUtil.writeString(result, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public void genModuleServicePom(String baseDir) {
        String moduleDir = String.format("%s/%s-service", baseDir, projectProperties.getAppProjectName());
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_SERVICE_POM), projectProperties);
        FileUtil.writeString(result, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public void genModuleWebPom(String baseDir) {
        String moduleDir = String.format("%s/%s-web", baseDir, projectProperties.getAppProjectName());
        // 1.??????pom
        String pomResult = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_WEB_POM), projectProperties);
        FileUtil.writeString(pomResult, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
        // 2.??????config
        genResourceCode(TemplateManagerConfig.APPLICATION, moduleDir, "", FileType.PROPERTIES);
        // 3.??????mybatis-config.xml
        genResourceCode(TemplateManagerConfig.MYBATIS_CONFIG, moduleDir, "", FileType.XML);

        // 4.??????AppApplication
        genMainJavaCode(TemplateManagerConfig.APP_APPLICATION, moduleDir, "web/", FileType.JAVA);
        // 5. ??????MybatisPlusConfig
        genMainJavaCode(TemplateManagerConfig.MY_BATIS_PLUS_CONFIG, moduleDir, "web/config/mybatis/", FileType.JAVA);
        // 6. ??????swaggerConfig
        genMainJavaCode(TemplateManagerConfig.SWAGGER_CONFIG, moduleDir, "web/config/swagger/", FileType.JAVA);
        // 7. ??????ReqRespConstant
        genMainJavaCode(TemplateManagerConfig.REQ_RESP_CONSTANT, moduleDir, "web/config/common/", FileType.JAVA);
        // 8. ??????openfeign??????????????????
        genMainJavaCode(TemplateManagerConfig.OPENFEIGN_INTERCEPTOR, moduleDir, "web/config/openfeign/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.FEIGN_SUPPORT_HEADER_CONFIG, moduleDir, "web/config/openfeign/", FileType.JAVA);
        // 9. ??????spring????????????, ??????????????????, ??????, ?????????
        genMainJavaCode(TemplateManagerConfig.LOG_RECORD_ASPECT, moduleDir, "web/config/springmvc/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.MY_EXCEPTION_HANDLER, moduleDir, "web/config/springmvc/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.SPRING_MVC_CONFIG, moduleDir, "web/config/springmvc/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.USER_INTERCEPTOR, moduleDir, "web/config/springmvc/", FileType.JAVA);

        // n.??????test?????????
        FileUtil.mkdir(String.format("%s/src/test/java/%s/web/", moduleDir, projectProperties.getBasePackagePath()));
    }

    /**
     * ??????main/java????????????
     *
     * @param fileName
     * @param moduleDir
     * @param srcPath
     * @param fileType
     */
    @SneakyThrows
    public void genMainJavaCode(String fileNameAndTemplateId, String moduleDir, String srcPath, FileType fileType) {
        genMainJavaCodeWithFileName(fileNameAndTemplateId, null, moduleDir, srcPath, fileType, null);
    }

    /**
     * ??????resource????????????
     *
     * @param fileName
     * @param moduleDir
     * @param srcPath
     * @param fileType
     */
    @SneakyThrows
    public void genResourceCode(String fileName, String moduleDir, String srcPath, FileType fileType) {
        genResourceCode(fileName, null, moduleDir, srcPath, fileType, null);
    }

    @SneakyThrows
    public void genResourceCode(String templateId, String fileName, String moduleDir, String srcPath, FileType fileType, Object model) {
        if (ObjectUtil.isEmpty(model)) {
            model = projectProperties;
        }
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(templateId), model);
        String resultPath = String.format("%s/src/main/resources/" + srcPath, moduleDir);
        if (StrUtil.isEmpty(fileName)) {
            fileName = templateId;
        }
        FileUtil.writeString(result, resultPath + fileName + fileType.getSuffix(), StandardCharsets.UTF_8);
    }

    @Override
    public void genAll(String baseDir) {
        // 1. ??????pom????????????
        genMainPom(baseDir);
        genModuleWebPom(baseDir);
        genModuleDaoPom(baseDir);
        genModuleClientPom(baseDir);
        genModuleCommonPom(baseDir);
        genModuleHandlerPom(baseDir);
        genModuleIntegrationPom(baseDir);
        genModuleServicePom(baseDir);
        // 2. ?????????????????????entity, vo, ext, converter, service-interface, service, controller, mapper-interface, mapper-xml
        String tableSchema = projectProperties.getTableSchema();
        LambdaQueryWrapper<Tables> lqw = new LambdaQueryWrapper<Tables>().eq(Tables::getTableSchema, tableSchema);
        List<Tables> tablesList = tablesService.list(lqw);
        for (Tables tables : tablesList) {
            // ??????????????????
            TableDefModel tableModel = new TableDefModel(tables, projectProperties);
            String tableName = tables.getTableName();
            LambdaQueryWrapper<Statistics> sLqw = new LambdaQueryWrapper<Statistics>().eq(Statistics::getTableSchema, tableSchema)
                    .eq(Statistics::getTableName, tableName);
            List<Statistics> statisticsList = statisticsService.list(sLqw);
            LambdaQueryWrapper<Columns> cLqw = new LambdaQueryWrapper<Columns>().eq(Columns::getTableSchema, tableSchema).eq(Columns::getTableName, tableName);
            List<Columns> columnsList = columnsService.list(cLqw);
            initTableDefModel(tableModel, statisticsList, columnsList);
            genTables(baseDir, tableModel);
        }
    }


    /**
     * ?????????????????????
     *
     * @param tableModel
     * @param statisticsList
     * @param columnsList
     */
    private void initTableDefModel(TableDefModel tableModel,
                                   List<Statistics> statisticsList,
                                   List<Columns> columnsList) {
        // ?????????????????????
        List<ColumnsModel> columnsModels = columnsList.stream()
                .map(item -> {
                    ColumnsModel columnsModel = new ColumnsModel();
                    columnsModel.setColumnName(StrUtil.toCamelCase(item.getColumnName()));
                    columnsModel.setSrcColumnName(item.getColumnName());
                    columnsModel.setColumnType(TypeConverter.getTypeName(item.getDataType()));
                    columnsModel.setColumnComment(item.getColumnComment());
                    columnsModel.setJdbcDataType(TypeConverter.getJdbcDateType(item.getDataType()));
                    return columnsModel;
                })
                .collect(Collectors.toList());
        tableModel.setColumnsList(columnsModels);

        Map<String, List<Statistics>> statisticsMap = statisticsList.stream()
                .filter(i -> !"PRIMARY".equals(i.getIndexName()))
                .filter(i -> i.getNonUnique() == 0).collect(Collectors.groupingBy(Statistics::getIndexName));
        List<UniqIndexModel> uniqIndexModels = statisticsMap.entrySet().stream().map(item -> {
            String key = item.getKey();
            List<Statistics> value = item.getValue();
            List<String> columnName = value.stream().map(Statistics::getColumnName).map(StrUtil::toCamelCase).collect(Collectors.toList());
            UniqIndexModel uniqueIndex = new UniqIndexModel();
            uniqueIndex.setMethodName("get" + StrUtil.upperFirst(StrUtil.toCamelCase(key)));
            uniqueIndex.setFields(columnName);
            return uniqueIndex;
        }).collect(Collectors.toList());
        tableModel.setUniqIndexModels(uniqIndexModels);
    }

    @Override
    public void genTables(String baseDir, TableDefModel tableModel) {
        String daoModuleDir = String.format("%s/%s-dao", baseDir, projectProperties.getAppProjectName());
        // ??????entity
        genMainJavaCodeWithFileName(TableTemplateManagerConfig.XXX_ENTITY,
                tableModel.getEntityName(), daoModuleDir,
                "dao/entity/", FileType.JAVA, tableModel);
        // ??????mapper-interface
        genMainJavaCodeWithFileName(TableTemplateManagerConfig.XXX_MAPPER,
                tableModel.getMapperName(), daoModuleDir,
                "dao/mapper/", FileType.JAVA, tableModel);
        // ?????? mapper-xml
        genResourceCode(TableTemplateManagerConfig.XXX_MAPPER_XML,
                tableModel.getMapperName(), daoModuleDir,
                "mapper/", FileType.XML, tableModel);
        // ??????vo
        String commonModuleDir = String.format("%s/%s-common", baseDir, projectProperties.getAppProjectName());
        genMainJavaCodeWithFileName(TableTemplateManagerConfig.XXX_VO,
                tableModel.getVoName(), commonModuleDir,
                "common/vo/", FileType.JAVA, tableModel);
        // ??????converter
        String serviceModuleDir = String.format("%s/%s-service", baseDir, projectProperties.getAppProjectName());
        genMainJavaCodeWithFileName(TableTemplateManagerConfig.XXX_CONVERTER,
                tableModel.getConverterName(), serviceModuleDir,
                "service/converter/", FileType.JAVA, tableModel);
        // ?????? service
        genMainJavaCodeWithFileName(TableTemplateManagerConfig.XXX_SERVICE,
                tableModel.getServiceName(), serviceModuleDir,
                "service/service/", FileType.JAVA, tableModel);
        // ?????? serviceImpl
        genMainJavaCodeWithFileName(TableTemplateManagerConfig.XXX_SERVICE_IMPL,
                tableModel.getServiceImplName(), serviceModuleDir,
                "service/service/impl/", FileType.JAVA, tableModel);

    }

    /**
     * @param templateId freemarker??????id
     * @param fileName   ??????????????????
     * @param moduleDir  ??????????????????
     * @param srcPath    java??????????????????
     * @param fileType   ????????????
     * @param model      freemarker ??????
     */
    @SneakyThrows
    private void genMainJavaCodeWithFileName(String templateId, String fileName, String moduleDir,
                                             String srcPath, FileType fileType, Object model) {
        if (ObjectUtil.isEmpty(model)) {
            model = projectProperties;
        }
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(templateId), model);
        String resultPath = String.format("%s/src/main/java/%s/%s/" + srcPath, moduleDir,
                projectProperties.getBasePackagePath(),
                projectProperties.getAppProjectNameToCamelCase());
        if (StrUtil.isEmpty(fileName)) {
            fileName = templateId;
        }
        FileUtil.writeString(result, resultPath + fileName + fileType.getSuffix(), StandardCharsets.UTF_8);
    }
}
