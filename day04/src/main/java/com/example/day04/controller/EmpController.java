package com.example.day04.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.day04.entity.Emp;
import com.example.day04.service.EmpServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "员工管理", description = "提供员工的增删改查、分页、条件删除等接口")
@RestController
@RequestMapping("/api/emp")
public class EmpController {

    @Autowired
    private EmpServiceImp empService;

    // 增加员工
    @PostMapping
    @Operation(summary = "新增员工")
    public Emp add(@RequestBody Emp emp) {
        empService.save(emp);
        return emp;
    }

    // 按给定id删除
    @DeleteMapping("/{id}")
    @Operation(summary = "根据ID删除员工")
    public void deleteById(@Parameter(description = "员工ID") @PathVariable Integer id) {
        empService.removeById(id);
    }

    // 按给定条件删除
    @DeleteMapping("/condition")
    @Operation(summary = "按部门删除员工")
    public boolean deleteByDept(@RequestParam String dept) {
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getDept, dept);
        return empService.remove(wrapper);
    }

    //  按给定id修改
    @PutMapping("/{id}")
    @Operation(summary = "根据ID修改员工")
    public Emp update(@PathVariable Integer id, @RequestBody Emp emp) {
        emp.setId(id);
        empService.updateById(emp);
        return emp;
    }

    // 显示全部数据
    @GetMapping
    @Operation(summary = "查询所有员工")
    public List<Emp> listAll() {
        return empService.list();
    }

    // 分页显示全部数据
    @GetMapping("/page")
    @Operation(summary = "分页查询所有员工")
    public IPage<Emp> page(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Emp> page = new Page<>(pageNum, pageSize);
        return empService.page(page);
    }

    // 按给定id查询数据
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询员工")
    public Emp getById(@PathVariable Integer id) {
        return empService.getById(id);
    }

    // 按给定条件分页查询数据
    @GetMapping("/page/condition")
    @Operation(summary = "条件分页查询（部门+最小年龄）")
    public IPage<Emp> pageByCondition(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String dept,
            @RequestParam(required = false) Integer minAge) {

        Page<Emp> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        if (dept != null && !dept.isEmpty()) {
            wrapper.eq(Emp::getDept, dept);
        }
        if (minAge != null) {
            wrapper.ge(Emp::getAge, minAge);
        }
        return empService.page(page, wrapper);
    }
}