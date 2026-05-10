package com.example.day03.config;

import com.example.day03.ennity.Student;
import com.example.day03.ennity.Teacher;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "")
public class AppConfig {
    private Teacher teacher;
    private List<Student> students;
}