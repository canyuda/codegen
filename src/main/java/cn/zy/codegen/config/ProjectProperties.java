package cn.zy.codegen.config;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "project")
@Data
@Component
public class ProjectProperties {
    private String appProjectName;
    private String outPath;
    private String basePackage;
    private String appProjectNameToCamelCase;

    private String tableSchema;

    public String getAppProjectNameToCamelCase() {
        if (StrUtil.isBlank(appProjectNameToCamelCase)) {
            return StrUtil.toCamelCase(appProjectName, '-');
        } else {
            return appProjectNameToCamelCase;
        }
    }

    public String getBasePackagePath() {
        if (StrUtil.isNotBlank(basePackage)) {
            return basePackage.replaceAll("\\.", "/");
        } else {
            return "cn/zy";
        }
    }
}
