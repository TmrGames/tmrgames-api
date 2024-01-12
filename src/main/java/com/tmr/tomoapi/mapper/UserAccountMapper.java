package com.tmr.tomoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmr.tomoapi.entity.SysAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper extends BaseMapper<SysAccount> {
}
