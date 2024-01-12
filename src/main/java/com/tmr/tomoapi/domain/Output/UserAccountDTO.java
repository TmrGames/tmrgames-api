package com.tmr.tomoapi.domain.Output;

import com.tmr.tomoapi.entity.SysBalance;
import com.tmr.tomoapi.entity.SysPrice;
import lombok.Data;

import java.util.List;

@Data
public class UserAccountDTO {
    private Long userId;
    private Long walletId;
    private String wallet;
    private Integer ticketBalance;
    private List<SysBalance> balanceList;
    private List<SysPrice> priceList;
}
