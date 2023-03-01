package cn.zy.codegen.service;

import freemarker.template.Template;

public interface TableScanService {
    Template getTemplate(String name);

    void genMainPom(String baseDir);

    void genModuleClientPom(String baseDir);

    void genModuleCommonPom(String baseDir);

    void genModuleDaoPom(String baseDir);

    void genModuleHandlerPom(String baseDir);

    void genModuleIntegrationPom(String baseDir);

    void genModuleServicePom(String baseDir);

    void genModuleWebPom(String baseDir);

    void genAllPom(String baseDir);
}
