<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>cn.zy</groupId>
        <artifactId>${appProjectName}</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>${appProjectName}-web</artifactId>
    <name>${appProjectName}-web</name>
    <properties>
        <java.version>1.8</java.version>
        <mybatisplus.version>3.3.1</mybatisplus.version>
        <maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
        <spring-cloud-alibaba-dependencies.version>2021.0.1.0</spring-cloud-alibaba-dependencies.version>
        <spring-cloud-dependencies.version>2021.0.1</spring-cloud-dependencies.version>
    </properties>
    <#assign dollar="$">
    <dependencies>
        <!--内置模块 -->
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-service</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-dao</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-common</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-client</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-integration</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>lowcode-sdk</artifactId>
        </dependency>
    </dependencies>
    <build>
        <finalName>${dollar}{project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>3.0.3</version>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>