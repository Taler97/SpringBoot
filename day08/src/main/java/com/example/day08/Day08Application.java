package com.example.day08;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.day08.mapper")
@SpringBootApplication
public class Day08Application {

    public static void main(String[] args) {
        SpringApplication.run(Day08Application.class, args);
    }

}
