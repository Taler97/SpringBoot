package com.example.day08.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {
    public static void main(String[] args) {
        // 1. 配置数据库连接信息
        String url = "jdbc:mysql://localhost:3306/springmvctest?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";

        // 2. 执行生成
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("Taler") // 设置作者
                            .outputDir("D:\\JDBC_Project\\springBoot\\day08\\src\\main\\java") // 指定输出目录
                            .enableSpringdoc()
                            .commentDate("yyyy-MM-dd");
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example.day08") // 设置父包名
                            .entity("entity") // 设置实体类包名
                            .mapper("mapper") // 设置Mapper接口包名
                            .service("service") // 设置Service接口包名
                            .serviceImpl("service.impl") // 设置Service实现类包名
                            .controller("controller") // 设置Controller包名
                            .xml("mapper.xml"); // 设置XML映射文件包名
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("channel") // 设置需要生成的表名，支持多张表
                            .addTablePrefix("t_") // 设置表名前缀，生成实体时会自动去除
                            // 实体类策略配置
                            .entityBuilder()
                            .enableLombok().
                            enableFileOverride()
                            .naming(NamingStrategy.underline_to_camel) // 数据库表名映射到实体类：下划线转驼峰
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库字段映射到实体属性：下划线转驼峰
                            // Controller策略配置
                            .controllerBuilder()
                            .enableRestStyle()
                            .enableRestStyle() // 开启@RestController注解
                            .enableHyphenStyle()// 开启驼峰转连字符（用于@PathVariable）

                            .serviceBuilder()
                            .mapperBuilder()
                            .enableFileOverride();
                })
                // 使用Freemarker模板引擎
                .templateEngine(new FreemarkerTemplateEngine())
                // 执行
                .execute();
    }
}