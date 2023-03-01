package cn.zy.codegen.config.template;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class TemplateManagerConfig {
    public static final String BASE_PATH;
    public static final String MAIN_POM = "mainPom";
    public static final String MODULE_COMMON_POM = "moduleCommonPom";
    public static final String MODULE_CLIENT_POM = "moduleClientPom";
    public static final String MODULE_DAO_POM = "moduleDaoPom";
    public static final String MODULE_HANDLER_POM = "moduleHandlerPom";
    public static final String MODULE_INTEGRATION_POM = "moduleIntegrationPom";
    public static final String MODULE_SERVICE_POM = "moduleServicePom";
    public static final String MODULE_WEB_POM = "moduleWebPom";
    public static final String META_HANDLER = "MetaHandler";
    public static final String APPLICATION = "application";
    public static final String APP_APPLICATION = "AppApplication";
    public static final String MYBATIS_CONFIG = "mybatis-config";
    public static final String MY_BATIS_PLUS_CONFIG = "MyBatisPlusConfig";
    public static final String SWAGGER_CONFIG = "SwaggerConfig";
    public static final String REQ_RESP_CONSTANT = "ReqRespConstant";
    public static final String OPENFEIGN_INTERCEPTOR = "OpenfeignInterceptor";
    public static final String FEIGN_SUPPORT_HEADER_CONFIG = "FeignSupportHeaderConfig";
    public static final String LOG_RECORD_ASPECT = "LogRecordAspect";
    public static final String MY_EXCEPTION_HANDLER = "MyExceptionHandler";
    public static final String SPRING_MVC_CONFIG = "SpringMvcConfig";
    public static final String USER_INTERCEPTOR = "UserInterceptor";

    static {
        try {
            BASE_PATH = new ClassPathResource("templates").getFile().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Bean(name = MAIN_POM)
    public Template mainPom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MAIN_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MAIN_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MODULE_CLIENT_POM)
    public Template moduleClientPom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MODULE_CLIENT_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MODULE_CLIENT_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MODULE_COMMON_POM)
    public Template moduleCommonPom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MODULE_COMMON_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MODULE_COMMON_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MODULE_DAO_POM)
    public Template moduleDaoPom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MODULE_DAO_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MODULE_DAO_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MODULE_HANDLER_POM)
    public Template moduleHandlerPom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MODULE_HANDLER_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MODULE_HANDLER_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MODULE_INTEGRATION_POM)
    public Template moduleIntegrationPom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MODULE_INTEGRATION_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MODULE_INTEGRATION_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MODULE_SERVICE_POM)
    public Template moduleServicePom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MODULE_SERVICE_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MODULE_SERVICE_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MODULE_WEB_POM)
    public Template moduleWebPom() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/pom/" + MODULE_WEB_POM + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MODULE_WEB_POM, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = META_HANDLER)
    public Template metaHandler() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/dao/" + META_HANDLER + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(META_HANDLER, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = APPLICATION)
    public Template application() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + APPLICATION + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(APPLICATION, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = APP_APPLICATION)
    public Template appApplication() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + APP_APPLICATION + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(APP_APPLICATION, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MYBATIS_CONFIG)
    public Template mybatisConfig() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + MYBATIS_CONFIG + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MYBATIS_CONFIG, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MY_BATIS_PLUS_CONFIG)
    public Template myBatisPlusConfig() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + MY_BATIS_PLUS_CONFIG + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MY_BATIS_PLUS_CONFIG, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = SWAGGER_CONFIG)
    public Template SwaggerConfig() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + SWAGGER_CONFIG + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(SWAGGER_CONFIG, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = REQ_RESP_CONSTANT)
    public Template ReqRespConstant() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + REQ_RESP_CONSTANT + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(REQ_RESP_CONSTANT, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = OPENFEIGN_INTERCEPTOR)
    public Template OpenfeignInterceptor() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + OPENFEIGN_INTERCEPTOR + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(OPENFEIGN_INTERCEPTOR, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = FEIGN_SUPPORT_HEADER_CONFIG)
    public Template FeignSupportHeaderConfig() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + FEIGN_SUPPORT_HEADER_CONFIG + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(FEIGN_SUPPORT_HEADER_CONFIG, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = LOG_RECORD_ASPECT)
    public Template LogRecordAspect() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + LOG_RECORD_ASPECT + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(LOG_RECORD_ASPECT, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = MY_EXCEPTION_HANDLER)
    public Template MyExceptionHandler() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + MY_EXCEPTION_HANDLER + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(MY_EXCEPTION_HANDLER, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = SPRING_MVC_CONFIG)
    public Template SpringMvcConfig() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + SPRING_MVC_CONFIG + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(SPRING_MVC_CONFIG, reader, configuration);
    }

    @SneakyThrows
    @Bean(name = USER_INTERCEPTOR)
    public Template UserInterceptor() {
        InputStreamReader reader = new InputStreamReader(FileUtil.getInputStream(BASE_PATH + "/java/web/" + USER_INTERCEPTOR + ".ftl"), StandardCharsets.UTF_8);
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINESE, "utf-8");
        return new Template(USER_INTERCEPTOR, reader, configuration);
    }
}
