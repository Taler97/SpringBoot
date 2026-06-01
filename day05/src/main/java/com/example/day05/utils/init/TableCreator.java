package com.example.day05.utils.init;

import com.example.day05.entity.Employee;
import com.example.day05.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
public class TableCreator {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @PostConstruct
    public void createTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = """
            CREATE TABLE IF NOT EXISTS employee (
                id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                emp_name VARCHAR(50) NOT NULL COMMENT '员工姓名',
                login_name VARCHAR(50) NOT NULL COMMENT '登录账号',
                login_password VARCHAR(100) NOT NULL COMMENT '登录密码',
                age INT COMMENT '年龄',
                gender VARCHAR(10) COMMENT '性别',
                addr VARCHAR(255) COMMENT '地址',
                dept_name VARCHAR(50) COMMENT '部门名称',
                status TINYINT DEFAULT 0 COMMENT '状态：0工作 1休息 2离职',
                deleted TINYINT DEFAULT 0 COMMENT '是否删除：0未删除 1已删除'
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';
            """;
        jdbcTemplate.execute(sql);
        System.out.println("done");
    }
    @PostConstruct
    public void initData() {
        // 1. 检查表中是否已有数据
        long count = employeeService.count();
        if (count == 0) {
            // 2. 没有数据则插入测试数据
            List<Employee> testData = buildTestData();
            employeeService.saveBatch(testData);
            System.out.println("初始化测试数据插入成功，共 " + testData.size() + " 条");
        } else {
            System.out.println("⚡ 员工表已有数据，跳过初始化插入");
        }
    }

    private List<Employee> buildTestData() {
        return Arrays.asList(
                createEmployee("张三", "zhangsan", "123456", 28, "男", "北京市朝阳区", "技术部", (byte) 0),
                createEmployee("李四", "lisi", "123456", 32, "男", "上海市浦东新区", "市场部", (byte) 0),
                createEmployee("王芳", "wangfang", "123456", 26, "女", "广州市天河区", "人事部", (byte) 0),
                createEmployee("赵磊", "zhaolei", "123456", 35, "男", "深圳市南山区", "技术部", (byte) 0),
                createEmployee("孙丽", "sunli", "123456", 29, "女", "杭州市西湖区", "财务部", (byte) 0),
                createEmployee("周强", "zhouqiang", "123456", 41, "男", "成都市高新区", "销售部", (byte) 1),
                createEmployee("吴敏", "wumin", "123456", 24, "女", "武汉市洪山区", "运营部", (byte) 0),
                createEmployee("郑浩", "zhenghao", "123456", 38, "男", "南京市鼓楼区", "技术部", (byte) 2),
                createEmployee("林晨", "linchen", "123456", 27, "女", "西安市雁塔区", "产品部", (byte) 0),
                createEmployee("郭峰", "guofeng", "123456", 33, "男", "长沙市岳麓区", "行政部", (byte) 1)
        );
    }
    private Employee createEmployee(String empName, String loginName, String loginPassword,
                                    Integer age, String gender, String addr,
                                    String deptName, Byte status) {
        Employee emp = new Employee();
        emp.setEmpName(empName);
        emp.setLoginName(loginName);
        emp.setLoginPassword(loginPassword);
        emp.setAge(age);
        emp.setGender(gender);
        emp.setAddr(addr);
        emp.setDeptName(deptName);
        emp.setStatus(status);
        return emp;
    }

}
