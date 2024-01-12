package com.tmr.tomoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmr.tomoapi.entity.SysTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper extends BaseMapper<SysTicket> {
}
