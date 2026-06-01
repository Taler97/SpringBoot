package com.example.day05.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.day05.entity.Employee;
import com.example.day05.mapper.EmployeeMapper;
import com.example.day05.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author Taler97
 * @since 2026-05-27
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Transactional
    public int updateByDepartment(String dept,String newDept) {
        LambdaUpdateWrapper<Employee> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Employee::getDeptName,dept)
                .set(Employee::getDeptName,newDept);
        int lines = baseMapper.update(null, wrapper);
        log.info("成功修改"+lines+"数据");
        return lines;
    }
    @Transactional
    public int updateByAddress(String address, String address2, int ageMin, int ageMax, Byte newStaus) {
        LambdaUpdateWrapper<Employee> wrapper = new LambdaUpdateWrapper<>();
        wrapper.and(w -> w.eq(Employee::getAddr, address)
                        .or()
                        .eq(Employee::getAddr, address2))
                .notBetween(Employee::getAge, ageMin, ageMax)
                .set(Employee::getStatus, (byte) 1);   // 假设 1 表示休息
        return baseMapper.update(null, wrapper);
    }
    @Transactional
    public int deleteByAge(Integer age) {
        LambdaUpdateWrapper<Employee> wrapper = new LambdaUpdateWrapper<>();
        wrapper.gt(Employee::getAge, age);
        return baseMapper.update(null, wrapper);
    }
}
