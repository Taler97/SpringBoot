package com.example.day07.utils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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

        jdbcTemplate.execute("DROP TABLE IF EXISTS `user`");

        String createTableSql = """
                CREATE TABLE `user` (
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
        System.out.println("用户表已重建");


        long id1 = IdWorker.getId();
        long id2 = IdWorker.getId();
        long id3 = IdWorker.getId();

        jdbcTemplate.execute(String.format(
                "INSERT INTO `user` (`id`, `name`, `password`, `status`) VALUES (%d, '张三', 'encrypt_123456', 0)", id1));
        jdbcTemplate.execute(String.format(
                "INSERT INTO `user` (`id`, `name`, `password`, `status`) VALUES (%d, '李四', 'encrypt_654321', 0)", id2));
        jdbcTemplate.execute(String.format(
                "INSERT INTO `user` (`id`, `name`, `password`, `status`) VALUES (%d, '王五', 'encrypt_111111', 1)", id3));

        System.out.println("测试数据插入完成，生成的ID: " + id1 + ", " + id2 + ", " + id3);
    }
}