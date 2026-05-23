package com.example.day04.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.day04.entity.Emp;
import com.example.day04.mapper.EmpMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImp extends ServiceImpl<EmpMapper,Emp> {
}
