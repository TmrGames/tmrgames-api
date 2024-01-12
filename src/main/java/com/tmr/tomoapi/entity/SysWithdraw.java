package com.tmr.tomoapi.entity;

import lombok.Data;

@Data
public class SysWithdraw extends AuditingEntity {
    private Long id;
    private Long balanceId;
    private String blockNo;
    private String txHash;
    private String fromAddress;
    private String toAddress;
    private String crypto;
    private String amount;
    private Integer status;
}
