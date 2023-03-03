package cn.zy.codegen.config.template;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class TableTemplateManagerConfig {

    public static final String XXX_ENTITY = "mybatis-plus-entity";
    public static final String XXX_MAPPER = "mybatis-plus-mapper";
    public static final String XXX_MAPPER_XML = "mybatis-plus-mapper-xml";
    public static final String XXX_VO = "mybatis-plus-entity-vo";
    public static final String XXX_CONVERTER = "mybatis-plus-entity-converter";
    public static final String XXX_SERVICE = "mybatis-plus-service";
    public static final String XXX_SERVICE_IMPL = "mybatis-plus-serviceImpl";

    @SneakyThrows
    @Bean(name = XXX_ENTITY)
    public Template XXX_ENTITY() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(TemplateManagerConfig.BASE_PATH + "/java/table/" + XXX_ENTITY + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(XXX_ENTITY, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = XXX_MAPPER)
    public Template XXX_MAPPER() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(TemplateManagerConfig.BASE_PATH + "/java/table/" + XXX_MAPPER + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(XXX_MAPPER, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = XXX_MAPPER_XML)
    public Template XXX_MAPPER_XML() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(TemplateManagerConfig.BASE_PATH + "/java/table/xml/" + XXX_MAPPER_XML + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(XXX_MAPPER_XML, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = XXX_VO)
    public Template XXX_VO() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(TemplateManagerConfig.BASE_PATH + "/java/table/" + XXX_VO + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(XXX_VO, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = XXX_CONVERTER)
    public Template XXX_CONVERTER() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(TemplateManagerConfig.BASE_PATH + "/java/table/" + XXX_CONVERTER + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(XXX_CONVERTER, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = XXX_SERVICE)
    public Template XXX_SERVICE() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(TemplateManagerConfig.BASE_PATH + "/java/table/" + XXX_SERVICE + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(XXX_SERVICE, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = XXX_SERVICE_IMPL)
    public Template XXX_SERVICE_IMPL() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(TemplateManagerConfig.BASE_PATH + "/java/table/" + XXX_SERVICE_IMPL + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(XXX_SERVICE_IMPL, reader, configuration);
    }
}
