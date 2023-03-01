package ${basePackage}.${appProjectNameToCamelCase}.web.config.openfeign;

import ${basePackage}.${appProjectNameToCamelCase}.web.config.openfeign.OpenfeignInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSupportHeaderConfig {
    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new OpenfeignInterceptor();
    }
}
