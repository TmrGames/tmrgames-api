package com.tmr.tomoapi.domain.Input;

import lombok.Data;

@Data
public class UserDepositInput {
    private String blockNo;
    private String txHash;
    private String fromAddress;
    private String toAddress;
    private String contractAddress;
    private String crypto;
    private String amount;
}
