package com.example.day07;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.day07.common.UserStatus;
import com.example.day07.entity.Account;
import com.example.day07.entity.User;

import com.example.day07.service.UserService;
import com.example.day07.service.impl.AccountServiceImp;
import com.example.day07.service.impl.UserServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class UserActiveRecordMainTest {

    public static void main(String[] args) {

      ConfigurableApplicationContext context = SpringApplication.run(UserActiveRecordMainTest.class, args);
        UserService userService = context.getBean(UserServiceImpl.class);
        AccountServiceImp accountServiceImp = context.getBean(AccountServiceImp.class);
        try {
            System.out.println("========== 开始 ActiveRecord 功能测试 ==========");
            User user = new User() ;
            user.setName("Test");
            user.setPassword("123456");
            user.setStatus(UserStatus.ACTIVE);
            userService.save(user);
            System.out.println("已保存Test用户");

            User userFound=userService.getById(user.getId());
            userFound.setName("Test2");
            System.out.println("已使用ActiveRecord修改用户名称为Test2");

            Account account = new Account() ;
            account.setId(user.getId());
            account.setName("TestAc1");
            account.setBalance(200);
            accountServiceImp.save(account);
            System.out.println("已保存Account：TestAc1");

            LambdaUpdateWrapper<Account> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Account::getId, user.getId());
            Account updateEntity = new Account();
            updateEntity.setBalance(950);
            accountServiceImp.update(updateEntity, wrapper);
            System.out.println("已将TestAc1余额修改为950");
            Account  account2= accountServiceImp.getById(user.getId());
            System.out.println(account2.getBalance());
            System.out.println("========== 所有测试执行完毕 ==========");
        } catch (Exception e) {
            System.err.println("测试执行出错: " + e.getMessage());
            e.printStackTrace();
        } finally {

            context.close();
        }
    }

}