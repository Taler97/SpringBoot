package com.example.day08.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.day08.entity.Channel;
import com.example.day08.service.impl.ChannelServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 控制器
 */
@Tag(name = "channel管理", description = "channel的增删改查接口")
@RestController
@RequestMapping("/Channel")
public class ChannelController {

    @Autowired
    private ChannelServiceImpl channelService;

    @Operation(summary = "新增channel")
    @PostMapping
    public boolean add(@RequestBody Channel Channel) {
        return channelService.save(Channel);
    }

    @Operation(summary = "删除channel（逻辑删除）")
    @DeleteMapping("/{aid}")
    public boolean delete(@Parameter(description = "channelID", required = true) @PathVariable Long aid) {
        return channelService.removeById(aid);
    }


    @Operation(summary = "修改channel")
    @PutMapping
    public boolean update(@RequestBody Channel Channel) {
        return channelService.updateById(Channel);
    }


    @Operation(summary = "根据ID查询channel")
    @GetMapping("/{aid}")
    public Channel getById(@Parameter(description = "channelID", required = true) @PathVariable Long aid) {
        return channelService.getById(aid);
    }


    @Operation(summary = "查询所有channel（未删除）")
    @GetMapping("/list")
    public List<Channel> listAll() {
        return channelService.list();
    }


    @Operation(summary = "分页查询channel")
    @GetMapping("/page")
    public Page<Channel> page(
            @Parameter(description = "当前页码", example = "1") @RequestParam(defaultValue = "1") long current,
            @Parameter(description = "每页条数", example = "10") @RequestParam(defaultValue = "10") long size,
            @Parameter(description = "栏目ID（可选）") @RequestParam(required = false) Integer cid,
            @Parameter(description = "channel标题（模糊查询，可选）") @RequestParam(required = false) String title) {
        Page<Channel> page = new Page<>(current, size);
        LambdaQueryWrapper<Channel> wrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            wrapper.eq(Channel::getCid, cid);
        }
        return channelService.page(page, wrapper);
    }
}
