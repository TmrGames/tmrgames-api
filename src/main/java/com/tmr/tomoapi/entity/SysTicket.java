package com.tmr.tomoapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysTicket extends AuditingEntity{
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Integer amount;
}
