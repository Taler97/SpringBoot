package com.example.day08.service.impl;

import com.example.day08.entity.Channel;
import com.example.day08.mapper.ChannelMapper;
import com.example.day08.service.IChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Taler
 * @since 2026-06-09
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements IChannelService {

}
