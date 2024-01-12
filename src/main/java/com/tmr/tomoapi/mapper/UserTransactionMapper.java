package com.tmr.tomoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmr.tomoapi.entity.SysTransaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTransactionMapper extends BaseMapper<SysTransaction> {
}
