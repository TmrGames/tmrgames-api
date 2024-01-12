package com.tmr.tomoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmr.tomoapi.entity.SysPrice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarketMapper extends BaseMapper<SysPrice> {
}
