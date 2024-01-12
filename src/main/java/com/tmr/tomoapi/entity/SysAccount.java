package com.tmr.tomoapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysAccount extends AuditingEntity{
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String wallet;

    private String walletKey;
}
