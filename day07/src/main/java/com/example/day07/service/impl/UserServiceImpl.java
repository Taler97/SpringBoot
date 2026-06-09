package com.example.day07.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.day07.entity.User;
import com.example.day07.mapper.UserMapper;
import com.example.day07.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@DS("db1")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}