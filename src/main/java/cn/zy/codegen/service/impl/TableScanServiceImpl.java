package cn.zy.codegen.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.zy.codegen.config.ProjectProperties;
import cn.zy.codegen.config.template.TemplateManagerConfig;
import cn.zy.codegen.enums.FileType;
import cn.zy.codegen.service.ColumnsService;
import cn.zy.codegen.service.StatisticsService;
import cn.zy.codegen.service.TableScanService;
import cn.zy.codegen.service.TablesService;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
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
        // 生成pom文件
        String pomResult = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_DAO_POM), projectProperties);
        FileUtil.writeString(pomResult, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
        // 生成dao包内的其他文件
        String metaHandlerPath = String.format("%s/src/main/java/%s/%s/dao/meta/handler/", moduleDir,
                projectProperties.getBasePackagePath(),
                projectProperties.getAppProjectNameToCamelCase());
        String metaHandler = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.META_HANDLER), projectProperties);
        FileUtil.writeString(metaHandler, metaHandlerPath + TemplateManagerConfig.META_HANDLER + ".java", StandardCharsets.UTF_8);
        // 创建test文件夹
        FileUtil.mkdir(String.format("%s/src/test/java/%s/dao/", moduleDir, projectProperties.getBasePackagePath()));
    }

    @SneakyThrows
    @Override
    public void genModuleHandlerPom(String baseDir) {
        String moduleDir = String.format("%s/%s-handler", baseDir, projectProperties.getAppProjectName());
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
        // 1.创建pom
        String pomResult = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(TemplateManagerConfig.MODULE_WEB_POM), projectProperties);
        FileUtil.writeString(pomResult, moduleDir + "/pom.xml", StandardCharsets.UTF_8);
        // 2.创建config
        genResourceCode(TemplateManagerConfig.APPLICATION, moduleDir, "", FileType.PROPERTIES);
        // 3.创建AppApplication
        genMainJavaCode(TemplateManagerConfig.APP_APPLICATION, moduleDir, "web/", FileType.JAVA);
        // 4.创建mybatis-config.xml
        genResourceCode(TemplateManagerConfig.MYBATIS_CONFIG, moduleDir, "", FileType.XML);
        // 5. 创建MybatisPlusConfig
        genMainJavaCode(TemplateManagerConfig.MY_BATIS_PLUS_CONFIG, moduleDir, "web/config/mybatis/", FileType.JAVA);
        // 6. 创建swaggerConfig
        genMainJavaCode(TemplateManagerConfig.SWAGGER_CONFIG, moduleDir, "web/config/swagger/", FileType.JAVA);
        // 7. 创建ReqRespConstant
        genMainJavaCode(TemplateManagerConfig.REQ_RESP_CONSTANT, moduleDir, "web/config/common/", FileType.JAVA);
        // 8. 创建openfeign拦截器和配置
        genMainJavaCode(TemplateManagerConfig.OPENFEIGN_INTERCEPTOR, moduleDir, "web/config/openfeign/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.FEIGN_SUPPORT_HEADER_CONFIG, moduleDir, "web/config/openfeign/", FileType.JAVA);
        // 9. 创建spring日志切面 统一异常处理 配置 拦截器
        genMainJavaCode(TemplateManagerConfig.LOG_RECORD_ASPECT, moduleDir, "web/config/springmvc/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.MY_EXCEPTION_HANDLER, moduleDir, "web/config/springmvc/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.SPRING_MVC_CONFIG, moduleDir, "web/config/springmvc/", FileType.JAVA);
        genMainJavaCode(TemplateManagerConfig.USER_INTERCEPTOR, moduleDir, "web/config/springmvc/", FileType.JAVA);

        // n.创建test文件夹
        FileUtil.mkdir(String.format("%s/src/test/java/%s/web/", moduleDir, projectProperties.getBasePackagePath()));
    }

    /**
     * 生成main/java下的文件
     *
     * @param fileName
     * @param moduleDir
     * @param srcPath
     * @param fileType
     */
    @SneakyThrows
    public void genMainJavaCode(String fileName, String moduleDir, String srcPath, FileType fileType) {
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(fileName), projectProperties);
        String resultPath = String.format("%s/src/main/java/%s/%s/" + srcPath, moduleDir,
                projectProperties.getBasePackagePath(),
                projectProperties.getAppProjectNameToCamelCase());
        FileUtil.writeString(result, resultPath + fileName + fileType.getSuffix(), StandardCharsets.UTF_8);
    }

    /**
     * 生成resource下的文件
     *
     * @param fileName
     * @param moduleDir
     * @param srcPath
     * @param fileType
     */
    @SneakyThrows
    public void genResourceCode(String fileName, String moduleDir, String srcPath, FileType fileType) {
        String mybatisConfigResult = FreeMarkerTemplateUtils.processTemplateIntoString(templateMap.get(fileName), projectProperties);
        String mybatisConfigPath = String.format("%s/src/main/resources/" + srcPath, moduleDir);
        FileUtil.writeString(mybatisConfigResult, mybatisConfigPath + fileName + fileType.getSuffix(), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public void genAllPom(String baseDir) {
        genMainPom(baseDir);
        genModuleWebPom(baseDir);
        genModuleDaoPom(baseDir);
        genModuleClientPom(baseDir);
        genModuleCommonPom(baseDir);
        genModuleHandlerPom(baseDir);
        genModuleIntegrationPom(baseDir);
        genModuleServicePom(baseDir);
    }
}
