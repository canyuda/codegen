package ${basePackage}.${appProjectNameToCamelCase}.web.config.springmvc;

import cn.zy.common.springmvc.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    <#assign dollar="$">
    @Value("${dollar}{zy.lowcode.remote-user-info-url}")
    private String userInfoUrl;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor(userInfoUrl))
                .addPathPatterns("/**");
    }
}
