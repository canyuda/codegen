package ${basePackage}.${appProjectNameToCamelCase}.web;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication(scanBasePackages = {"${basePackage}.${appProjectNameToCamelCase}", "${basePackage}"})
@MapperScan(basePackages="${basePackage}.${appProjectNameToCamelCase}.dao")
@EnableApolloConfig
@EnableOpenApi
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
