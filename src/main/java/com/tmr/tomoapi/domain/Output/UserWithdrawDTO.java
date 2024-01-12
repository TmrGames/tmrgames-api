package com.tmr.tomoapi.domain.Output;

import lombok.Data;

import java.util.Date;

@Data
public class UserWithdrawDTO {
    private String crypto;
    private String amount;
    private Date createTime;
}
