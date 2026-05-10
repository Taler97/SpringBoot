package com.example.day03.controller;



import com.example.day03.ennity.Student;
import com.example.day03.ennity.Teacher;
import com.example.day03.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/school")
public class TeacherStudentController {

    @Autowired
    private SchoolService schoolService;

    @Value("${community.platform}")
    private String communityPlatform;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/teacher")
    public Map<String, Object> getTeacherInfo() {
        Map<String, Object> response = new HashMap<>();
        Teacher teacher = schoolService.getTeacher();

        response.put("code", 200);
        response.put("message", "获取教师信息成功");
        response.put("data", teacher);
        response.put("environment", getEnvironmentInfo());

        return response;
    }

//每个学生的三科平均成绩
    @GetMapping("/student/averages")
    public Map<String, Object> getStudentAverages() {
        Map<String, Object> response = new HashMap<>();
        Map<String, Double> studentAverages = schoolService.calculateEachStudentAverage();
        List<Student> students = schoolService.getAllStudents();

        Map<String, Object> detailedData = new HashMap<>();
        detailedData.put("学生列表", students);
        detailedData.put("学生平均成绩", studentAverages);

        response.put("code", 200);
        response.put("message", "获取学生平均成绩成功");
        response.put("data", detailedData);
        response.put("environment", getEnvironmentInfo());

        return response;
    }

    // 语文平均成绩
    @GetMapping("/chinese/average")
    public Map<String, Object> getChineseAverage() {
        Map<String, Object> response = new HashMap<>();
        double chineseAverage = schoolService.calculateChineseAverage();
        List<Student> students = schoolService.getAllStudents();

        Map<String, Object> detailedData = new HashMap<>();
        detailedData.put("语文平均成绩", chineseAverage + "分");
        detailedData.put("学生语文成绩详情", getStudentChineseScores(students));

        response.put("code", 200);
        response.put("message", "获取语文平均成绩成功");
        response.put("data", detailedData);
        response.put("environment", getEnvironmentInfo());

        return response;
    }

    // 获取学生语文成绩
    private Map<String, Integer> getStudentChineseScores(List<Student> students) {
        Map<String, Integer> chineseScores = new HashMap<>();
        for (Student student : students) {
            chineseScores.put(student.getName(), student.getScore().getChinese());
        }
        return chineseScores;
    }

    // 获取环境信息
    private Map<String, String> getEnvironmentInfo() {
        Map<String, String> envInfo = new HashMap<>();
        envInfo.put("serverPort", serverPort);
        envInfo.put("communityPlatform", communityPlatform);

        // 根据端口判断环境
        if ("8080".equals(serverPort)) {
            envInfo.put("environment", "测试环境");
        } else if ("8081".equals(serverPort)) {
            envInfo.put("environment", "开发环境");
        } else if ("8082".equals(serverPort)) {
            envInfo.put("environment", "生产环境");
        }

        return envInfo;
    }
}