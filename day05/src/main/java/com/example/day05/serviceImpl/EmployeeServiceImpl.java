package com.example.day05.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.day05.entity.Employee;
import com.example.day05.mapper.EmployeeMapper;
import com.example.day05.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Employee> selectByAge(Integer ageMin, Integer ageMax) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(Employee::getAge,ageMin,ageMax);
        return  baseMapper.selectList(wrapper);
    }
    @Transactional
    public List<Employee> selectByDeptAndGender(String dept,String dept2, String gender) {

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w -> w.eq(Employee::getGender,gender))
                .eq(Employee::getDeptName,dept)
                .or()
                .eq(Employee::getDeptName,dept2);
        return  baseMapper.selectList(wrapper);
    }
    @Transactional
    public List<Employee> selectByAddrAndStaus(String address, Byte staus) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w -> w.eq(Employee::getAddr,address).eq(Employee::getStatus,staus));
        return  baseMapper.selectList(wrapper);
    }
    @Transactional
    public List<Employee> selectByAccount(String account,String gender,String dept) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w->w.like(Employee::getLoginName,account)
                .eq(Employee::getGender,gender)
                .or()
                .eq(Employee::getDeptName,dept));
        return  baseMapper.selectList(wrapper);
    }
    public Page<Employee> queryAllOrderByAgeDesc(int current) {
        Page<Employee> page = new Page<>(current, 5);

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Employee::getAge);

        return this.page(page, wrapper);
    }
}
