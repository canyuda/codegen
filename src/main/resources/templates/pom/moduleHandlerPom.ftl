<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>cn.zy</groupId>
        <artifactId>${appProjectName}</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>${appProjectName}-handle</artifactId>
    <name>${appProjectName}-handle</name>
    <#assign dollar="$">
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!--内置模块 -->
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-service</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-common</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.zy</groupId>
            <artifactId>${appProjectName}-integration</artifactId>
        </dependency>

    </dependencies>


</project>