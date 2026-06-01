package com.example.day05.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author Taler97
 * @since 2026-05-27
 */
@Getter
@Setter
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录密码
     */
    @TableField(select = false)
    private String loginPassword;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 地址
     */
    private String addr;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 状态：0工作 1休息 2离职
     */
    private Byte status;

    /**
     * 是否删除：0未删除 1已删除
     */
    @TableLogic
    private Byte deleted;
}
