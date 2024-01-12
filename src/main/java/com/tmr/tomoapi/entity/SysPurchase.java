package com.tmr.tomoapi.entity;

import lombok.Data;

@Data
public class SysPurchase extends AuditingEntity{
    private Long id;
    private Long balanceId;
    private Integer type;
    private String requestId;
    private String app;
    private String crypto;
    private String amount;
    private String usdtValue;
}
