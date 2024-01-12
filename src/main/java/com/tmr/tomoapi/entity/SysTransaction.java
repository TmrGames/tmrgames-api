package com.tmr.tomoapi.entity;

import lombok.Data;

@Data
public class SysTransaction extends AuditingEntity{
    private Long id;
    private Long balanceId;
    private Integer type;
    private String blockNo;
    private String txHash;
    private String fromAddress;
    private String toAddress;
    private String crypto;
    private String amount;
}
