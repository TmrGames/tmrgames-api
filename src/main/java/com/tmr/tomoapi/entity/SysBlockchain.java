package com.tmr.tomoapi.entity;

import lombok.Data;

@Data
public class SysBlockchain extends AuditingEntity{
    private Long id;
    private String blockchain;
    private String currentBlockNo;
}
