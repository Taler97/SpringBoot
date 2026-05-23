package com.example.day04;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.day04.mapper")
@SpringBootApplication
public class Day04Application {

    public static void main(String[] args) {
        SpringApplication.run(Day04Application.class, args);

    }}
