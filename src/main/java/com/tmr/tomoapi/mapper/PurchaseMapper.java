package com.tmr.tomoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmr.tomoapi.entity.SysPurchase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper extends BaseMapper<SysPurchase> {
}
