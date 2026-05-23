package com.example.day04.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class DatabaseInitializer {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = """
            CREATE TABLE IF NOT EXISTS emp (
                id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                name VARCHAR(255) COMMENT '姓名',
                addr VARCHAR(255) COMMENT '地址',
                dept VARCHAR(255) COMMENT '部门',
                age INT COMMENT '年龄'
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';
            """;
        jdbcTemplate.execute(sql);
        System.out.println("表 emp 初始化完成");
    }
}