package com.example.day03.service;

import com.example.day03.config.AppConfig;
import com.example.day03.ennity.Student;
import com.example.day03.ennity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolService {

    @Autowired
    private AppConfig appConfig;

    public Teacher getTeacher() {
        return appConfig.getTeacher();
    }

    public List<Student> getAllStudents() {
        return appConfig.getStudents();
    }

    public Map<String, Double> calculateEachStudentAverage() {
        Map<String, Double> studentAverages = new HashMap<>();
        List<Student> students = appConfig.getStudents();

        for (Student student : students) {
            double average = (student.getScore().getChinese()
                    + student.getScore().getMath()
                    + student.getScore().getEnglish()) / 3.0;
            studentAverages.put(student.getName(), Math.round(average * 100) / 100.0);
        }

        return studentAverages;
    }

    // 语文平均成绩
    public double calculateChineseAverage() {
        List<Student> students = appConfig.getStudents();
        if (students == null || students.isEmpty()) {
            return 0;
        }

        int totalChinese = 0;
        for (Student student : students) {
            totalChinese += student.getScore().getChinese();
        }

        double average = (double) totalChinese / students.size();
        return Math.round(average * 100) / 100.0;
    }
}