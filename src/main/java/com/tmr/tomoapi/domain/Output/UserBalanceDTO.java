package com.tmr.tomoapi.domain.Output;

import lombok.Data;

@Data
public class UserBalanceDTO {
    private Long id;
    private Long walletId;
    private String crypto;
    private String amount;
}
