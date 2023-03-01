<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <modules>
        <module>${appProjectName}-dao</module>
        <module>${appProjectName}-service</module>
        <module>${appProjectName}-web</module>
        <module>${appProjectName}-client</module>
        <module>${appProjectName}-common</module>
        <module>${appProjectName}-handler</module>
        <module>${appProjectName}-integration</module>
    </modules>
    <parent>
        <groupId>cn.zy</groupId>
        <artifactId>zy-springboot-starter-parent</artifactId>
        <version>1.0.2-RELEASE</version>
    </parent>
    <groupId>cn.zy</groupId>
    <artifactId>${appProjectName}</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <name>${appProjectName}</name>
    <description>${appProjectName} for Spring Boot</description>

    <properties>
        <oa-test-demo.version>1.0-SNAPSHOT</oa-test-demo.version>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring-cloud-openfeign.version>3.1.1</spring-cloud-openfeign.version>
        <org.mapstruct.version>1.5.3.Final</org.mapstruct.version>
        <lombok.version>1.18.24</lombok.version>
        <mybatisplus.version>3.3.1</mybatisplus.version>
        <lowcode-sdk.version>0.0.1-SNAPSHOT</lowcode-sdk.version>
        <zy-common.version>1.0.1-SNAPSHOT</zy-common.version>
    </properties>
    <#assign dollar="$">
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>${appProjectName}-common</artifactId>
                <version>${dollar}{${appProjectName}.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>${appProjectName}-dao</artifactId>
                <version>${dollar}{${appProjectName}.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>${appProjectName}-service</artifactId>
                <version>${dollar}{${appProjectName}.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>${appProjectName}-integration</artifactId>
                <version>${dollar}{${appProjectName}.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>${appProjectName}-handler</artifactId>
                <version>${dollar}{${appProjectName}.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>${appProjectName}-web</artifactId>
                <version>${dollar}{${appProjectName}.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>${appProjectName}-client</artifactId>
                <version>${dollar}{${appProjectName}.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>lowcode-sdk</artifactId>
                <version>${dollar}{lowcode-sdk.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.zy</groupId>
                <artifactId>zy-common</artifactId>
                <version>${dollar}{zy-common.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>com.ctrip.framework.apollo</groupId>
            <artifactId>apollo-client</artifactId>
        </dependency>

        <dependency>
            <groupId>com.ctrip.framework.apollo</groupId>
            <artifactId>apollo-core</artifactId>
        </dependency>

        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>23.0</version>
        </dependency>

        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
        </dependency>

        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>zy-base-common</artifactId>
            <version>1.0.2-RELEASE</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
        </dependency>

        <!--nacos配置中心 可以动态设置配置文件
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        -->

        <!-- nacos服务注册与发现 -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-commons</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <version>3.2.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <version>3.2.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
            <version>${dollar}{lombok.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${dollar}{org.mapstruct.version}</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${dollar}{org.mapstruct.version}</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.83</version>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>zy-common</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${dollar}{lombok.version}</version>
                        </path>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${dollar}{org.mapstruct.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>