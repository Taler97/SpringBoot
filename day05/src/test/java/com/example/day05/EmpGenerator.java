package com.example.day05;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collections;

public class EmpGenerator {
    public static void main(String[] args) throws URISyntaxException {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/springmvctest?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
        ).globalConfig(builder -> {
            builder.author("Taler97")
                    .outputDir("day05/src/main/java")
                    .commentDate("YYYY-MM-dd");
        }).packageConfig(builder -> {
            builder.parent("com.example.day05")
                    .entity("entity")
                    .mapper("mapper")
                    .service("service")
                    .controller("controller")
                    .serviceImpl("serviceImpl")
                    .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\JDBC_Project\\springBoot\\day05\\src\\main\\resources\\mapper"));
        }).strategyConfig(builder -> {
            builder.addInclude("employee")
                    //Entity
                    .entityBuilder()
                        .enableLombok()
                        .idType(IdType.AUTO)
                        .logicDeleteColumnName("deleted")
                    //controller
                    .controllerBuilder()
                        .enableRestStyle()
                    //service
                    .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                    //mapper
                    .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper");

        }).templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
