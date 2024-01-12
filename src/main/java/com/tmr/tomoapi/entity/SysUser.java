package com.tmr.tomoapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysUser extends AuditingEntity{
    @TableId(type = IdType.AUTO)
    private Long id;

    private String loginName;

    private String email;

    private String phone;

    private String password;

    private String salt;

    private String country;
}
