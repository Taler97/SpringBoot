package com.example.day07.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.day07.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
