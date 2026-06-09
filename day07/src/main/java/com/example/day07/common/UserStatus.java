package com.example.day07.common;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    ACTIVE(0, "激活"),
    INACTIVE(1, "未激活");

    @EnumValue  // 标记数据库存储的值
    private final Integer code;
    private final String desc;

    UserStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @JsonValue
    public String getDesc() {
        return desc;
    }

    public static UserStatus fromCode(Integer code) {
        for (UserStatus status : UserStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}