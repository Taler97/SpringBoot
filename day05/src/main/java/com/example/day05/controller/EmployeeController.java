package com.example.day05.controller;

import com.example.day05.entity.Employee;
import com.example.day05.serviceImpl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author Taler97
 * @since 2026-05-27
 */
@RestController
@RequestMapping("/employee")
@Tag(name = "员工管理", description = "提供员工信息的增删改查接口")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping
    @Operation(summary = "新增员工", description = "接收JSON格式的员工信息，保存到数据库")
    public Employee save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    @GetMapping
    @Operation(summary = "查询所有员工", description = "返回所有未删除的员工列表（不包含密码字段）")
    public List<Employee> findAll() {
        return employeeService.list();
    }

    @PutMapping("/department/{oldDept}")
    @Operation(summary = "批量转移部门", description = "将指定部门下的所有员工转移到新部门")
    public int updateDepartment(
            @Parameter(description = "原部门名称", example = "市场部")
            @PathVariable("oldDept") String oldDept,
            @Parameter(description = "目标部门名称", example = "销售部")
            @RequestParam("newDept") String newDept) {
        return employeeService.updateByDepartment(oldDept, newDept);
    }

    @PutMapping("/status/batch")
    @Operation(summary = "批量更新员工状态", description = "根据地址组合和年龄段以外的条件批量更新员工状态")
    public int batchUpdateStatus(
            @Parameter(description = "地址1", example = "吉林")
            @RequestParam String addr1,
            @Parameter(description = "地址2", example = "长春")
            @RequestParam String addr2,
            @Parameter(description = "最小年龄（不包含在此年龄段内）", example = "18")
            @RequestParam Integer ageMin,
            @Parameter(description = "最大年龄（不包含在此年龄段内）", example = "30")
            @RequestParam Integer ageMax,
            @Parameter(description = "新状态：0工作 1休息 2离职", example = "1")
            @RequestParam Byte newStatus) {
        return employeeService.updateByAddress(addr1, addr2, ageMin, ageMax, newStatus);
    }

    @DeleteMapping("/delete/{age}")
    @Operation(summary = "按年龄删除员工", description = "删除年龄小于指定值的员工")
    public int delete(
            @Parameter(description = "年龄上限（删除 age < 该值的员工）", example = "20")
            @PathVariable("age") Integer age) {
        return employeeService.deleteByAge(age);
    }
}