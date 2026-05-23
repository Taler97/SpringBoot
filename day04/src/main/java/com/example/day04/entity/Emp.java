package com.example.day04.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("emp")
@Schema(description = "员工实体")
public class Emp {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID", example = "1")
    private Integer id;

    @Schema(description = "姓名", example = "张三")
    private String name;

    @Schema(description = "地址", example = "北京市朝阳区")
    private String addr;

    @Schema(description = "部门", example = "技术部")
    private String dept;

    @Schema(description = "年龄", example = "25")
    private Integer age;
}