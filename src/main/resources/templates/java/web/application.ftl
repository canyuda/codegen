# apollo配置
apollo.bootstrap.enabled=true
apollo.meta=http://192.168.8.73:8070
app.id=${appProjectName}

# log配置
logging.file.config=classpath:logback-spring.xml

# web配置
server.port=8080
server.servlet.context-path=/
<#assign dollar="$">
# spring配置
spring.application.name=${appProjectName}
spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER
spring.profiles.active=${dollar}{env:dev}

# nacos配置 暂时不用nacos 配置中心
# spring.cloud.nacos.config.file-extension=properties
# spring.cloud.nacos.config.group=DEFAULT_GROUP
# spring.cloud.nacos.config.server-addr=192.168.8.73:8848
spring.cloud.nacos.discovery.server-addr=192.168.8.73:8848

# db配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/${appProjectName}?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.password=123456
spring.datasource.username=root

# zy配置
zy.lowcode.app-code=${dollar}{spring.application.name}
zy.lowcode.remote-auth-enabled=true
zy.lowcode.remote-auth-url=http://192.168.8.73/api/oauth/authStatus?token=%s
zy.lowcode.remote-user-info-url=http://192.168.8.73/api/oa/oauth/userinfo

# mybatis-plus配置
mybatis-plus.global-config.db-config.logic-delete-field=flag
mybatis-plus.global-config.db-config.logic-delete-value=1
mybatis-plus.global-config.db-config.logic-not-delete-value=0
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.slf4j.Slf4jImpl
mybatis-plus.mapper-locations=classpath:mappers/*.xml
