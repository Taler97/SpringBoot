package com.example.day04.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.day04.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
}
