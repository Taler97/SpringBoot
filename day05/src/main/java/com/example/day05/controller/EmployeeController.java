package com.example.day05.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.day05.entity.Employee;
import com.example.day05.entity.Result;
import com.example.day05.serviceImpl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "员工管理", description = "员工信息相关接口")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    //  批量更新部门名称
    @Operation(summary = "批量更新部门名称", description = "将指定部门的所有员工部门名修改为新部门名")
    @PutMapping("/update/dept")
    public Result<Integer> updateByDepartment(
            @Parameter(description = "原部门名称", example = "技术部") @RequestParam String dept,
            @Parameter(description = "新部门名称", example = "研发中心") @RequestParam String newDept) {
        int lines = employeeService.updateByDepartment(dept, newDept);
        return Result.success(lines);
    }

    // 根据地址和年龄范围更新员工状态
    @Operation(summary = "条件更新员工状态", description = "将地址为 address 或 address2，且年龄不在 [ageMin, ageMax] 范围内的员工状态更新为休息(1)")
    @PutMapping("/update/statusByAddrAndAge")
    public Result<Integer> updateByAddress(
            @Parameter(description = "地址1", example = "北京") @RequestParam String address,
            @Parameter(description = "地址2", example = "上海") @RequestParam String address2,
            @Parameter(description = "最小年龄", example = "18") @RequestParam int ageMin,
            @Parameter(description = "最大年龄", example = "60") @RequestParam int ageMax,
            @Parameter(description = "新状态(1=休息)", example = "1") @RequestParam Byte newStaus) {
        int lines = employeeService.updateByAddress(address, address2, ageMin, ageMax, newStaus);
        return Result.success(lines);
    }

    // 根据年龄范围查询员工
    @Operation(summary = "年龄区间查询", description = "查询年龄在 [minAge, maxAge] 之间的员工列表")
    @GetMapping("/select/age")
    public Result<List<Employee>> selectByAge(
            @Parameter(description = "最小年龄", example = "22") @RequestParam Integer ageMin,
            @Parameter(description = "最大年龄", example = "30") @RequestParam Integer ageMax) {
        List<Employee> list = employeeService.selectByAge(ageMin, ageMax);
        return Result.success(list);
    }

    // 根据部门（两个部门之一）和性别查询
    @Operation(summary = "多部门+性别查询", description = "查询部门为 dept 或 dept2，且性别为指定值的员工")
    @GetMapping("/select/deptAndGender")
    public Result<List<Employee>> selectByDeptAndGender(
            @Parameter(description = "部门1", example = "市场部") @RequestParam String dept,
            @Parameter(description = "部门2", example = "销售部") @RequestParam String dept2,
            @Parameter(description = "性别", example = "女") @RequestParam String gender) {
        List<Employee> list = employeeService.selectByDeptAndGender(dept, dept2, gender);
        return Result.success(list);
    }

    // 根据地址和状态查询
    @Operation(summary = "地址+状态精确查询", description = "查询地址和状态都匹配的员工")
    @GetMapping("/select/addrAndStatus")
    public Result<List<Employee>> selectByAddrAndStatus(
            @Parameter(description = "地址", example = "长春") @RequestParam String address,
            @Parameter(description = "状态(0工作 1休息 2离职)", example = "1") @RequestParam Byte status) {
        List<Employee> list = employeeService.selectByAddrAndStaus(address, status);
        return Result.success(list);
    }

    //  根据登录名模糊、性别或部门查询
    @Operation(summary = "账号模糊+性别或部门查询", description = "登录名包含 account，且（性别=gender 或 部门=dept）")
    @GetMapping("/select/account")
    public Result<List<Employee>> selectByAccount(
            @Parameter(description = "登录名关键字", example = "admin") @RequestParam String account,
            @Parameter(description = "性别", example = "男") @RequestParam String gender,
            @Parameter(description = "部门", example = "人事部") @RequestParam String dept) {
        List<Employee> list = employeeService.selectByAccount(account, gender, dept);
        return Result.success(list);
    }

    // 分页查询所有员工（按年龄降序，每页5条）
    @Operation(summary = "分页查询所有员工", description = "按年龄降序排列，每页5条数据")
    @GetMapping("/page/ageDesc")
    public Result<Page<Employee>> queryAllOrderByAgeDesc(
            @Parameter(description = "当前页码，默认1", example = "1") @RequestParam(defaultValue = "1") Integer current) {
        Page<Employee> page = employeeService.queryAllOrderByAgeDesc(current);
        return Result.success(page);
    }
}