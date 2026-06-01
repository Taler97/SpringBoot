package com.example.day05.mapper;

import com.example.day05.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工表 Mapper 接口
 * </p>
 *
 * @author Taler97
 * @since 2026-05-27
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
