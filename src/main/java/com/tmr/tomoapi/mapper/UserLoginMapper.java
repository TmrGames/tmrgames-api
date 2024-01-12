package com.tmr.tomoapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmr.tomoapi.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper extends BaseMapper<SysUser> {
}
