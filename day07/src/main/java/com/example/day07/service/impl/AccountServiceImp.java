package com.example.day07.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.day07.entity.Account;
import com.example.day07.mapper.AccountMapper;
import com.example.day07.service.AccountService;
import org.springframework.stereotype.Service;
@DS("db2")
@Service
public class AccountServiceImp extends ServiceImpl<AccountMapper, Account> implements AccountService {


}
