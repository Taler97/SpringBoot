package com.example.day07.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private int balance;
    @Version
    private Integer version;
}
