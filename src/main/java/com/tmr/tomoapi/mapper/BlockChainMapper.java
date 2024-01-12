package com.tmr.tomoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmr.tomoapi.entity.SysBlockchain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlockChainMapper extends BaseMapper<SysBlockchain> {
}
