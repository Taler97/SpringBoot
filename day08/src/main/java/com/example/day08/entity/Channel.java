package com.example.day08.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Taler
 * @since 2026-06-09
 */
@Getter
@Setter
@Schema(name = "Channel", description = "")
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "栏目id")
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    @Schema(description = "栏目名称")
    private String cname;

    @Schema(description = "栏目描述")
    private String channelDesc;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    @TableLogic
    @Schema(description = "是否删除")
    private Byte deleted;
}
