package com.example.mybatis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mybatis")
public class MybatisProperties {

    /**
     * 实体类包路径
     */
    private String typeAliasesPackage = "com.example.entity";

    /**
     * Mapper XML 文件位置
     */
    private String mapperLocations = "classpath*:mapper/**/*.xml";

    /**
     * 是否开启驼峰命名转换
     */
    private boolean mapUnderscoreToCamelCase = true;

    /**
     * 日志实现
     */
    private String logImpl = "org.apache.ibatis.logging.stdout.StdOutImpl";
}