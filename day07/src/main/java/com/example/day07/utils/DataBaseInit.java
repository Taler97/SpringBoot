package com.example.day07.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBaseInit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS `user` (
                    `id` BIGINT NOT NULL COMMENT '雪花算法主键',
                    `name` VARCHAR(50) NOT NULL COMMENT '用户名',
                    `password` VARCHAR(100) NOT NULL COMMENT '密码',
                    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0激活，1未激活',
                    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                    PRIMARY KEY (`id`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
                """;
        jdbcTemplate.execute(createTableSql);

        // 插入测试数据
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `user`", Integer.class);
        if (count == 0) {
            // 使用雪花算法主键（手动指定一些测试 ID，或者用 MyBatis-Plus 自动生成，这里直接写固定 ID）
            jdbcTemplate.execute("INSERT INTO `user` (`id`, `name`, `password`, `status`) VALUES (1, '张三', 'encrypt_123456', 0)");
            jdbcTemplate.execute("INSERT INTO `user` (`id`, `name`, `password`, `status`) VALUES (2, '李四', 'encrypt_654321', 0)");
            jdbcTemplate.execute("INSERT INTO `user` (`id`, `name`, `password`, `status`) VALUES (3, '王五', 'encrypt_111111', 1)");

        }
    }
}