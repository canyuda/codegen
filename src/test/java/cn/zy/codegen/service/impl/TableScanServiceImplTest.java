package cn.zy.codegen.service.impl;

import cn.zy.codegen.config.ProjectProperties;
import cn.zy.codegen.config.template.TemplateManagerConfig;
import cn.zy.codegen.service.TableScanService;
import freemarker.template.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
@DisplayName(value = "测试表扫描")
class TableScanServiceImplTest {

    @Autowired
    private TableScanService tableScanService;
    @Autowired
    private ProjectProperties projectProperties;

    private String baseDir;

    @BeforeEach
    void before() {
        baseDir = projectProperties.getOutPath() + File.separator + projectProperties.getAppProjectName();
    }

    @Test
    @DisplayName(value = "测试模板获取")
    void getTemplate() {
        Template template = tableScanService.getTemplate(TemplateManagerConfig.MAIN_POM);
        Assertions.assertNotNull(template);
    }

    @Test
    void genMainPom() {
        tableScanService.genMainPom(baseDir);
    }

    @Test
    void genModuleClientPom() {
        tableScanService.genModuleClientPom(baseDir);
    }

    @Test
    void genModuleCommonPom() {
        tableScanService.genModuleCommonPom(baseDir);
    }

    @Test
    void genModuleDaoPom() {
        tableScanService.genModuleDaoPom(baseDir);
    }

    @Test
    void genModuleHandlerPom() {
        tableScanService.genModuleHandlerPom(baseDir);
    }

    @Test
    void genModuleIntegrationPom() {
        tableScanService.genModuleIntegrationPom(baseDir);
    }

    @Test
    void genModuleServicePom() {
        tableScanService.genModuleServicePom(baseDir);
    }

    @Test
    void genModuleWebPom() {
        tableScanService.genModuleWebPom(baseDir);
    }
}