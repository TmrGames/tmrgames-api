package com.tmr.tomoapi.entity;

import lombok.Data;

@Data
public class SysPrice extends AuditingEntity{
    private Long id;
    private String symbol;
    private String price;
}
