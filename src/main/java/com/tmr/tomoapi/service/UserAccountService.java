package com.tmr.tomoapi.service;

import com.tmr.tomoapi.domain.Input.*;
import com.tmr.tomoapi.domain.Output.UserAccountDTO;
import com.tmr.tomoapi.domain.Output.UserTicketDTO;
import com.tmr.tomoapi.domain.Output.UserWithdrawDTO;

public interface UserAccountService {

    UserAccountDTO getBalance(UserAccountInput userAccountInput);

    Boolean depositTomo(UserDepositInput userDepositInput);

    String getLastBlockNo();

    void updateLastBlockNo(String blockNo);

    UserTicketDTO buyTicket(UserTicketInput userTicketInput);

    Boolean withdraw(UserWithdrawInput userWithdrawInput);

    UserWithdrawDTO getWithdraw(UserWithdrawQueryInput userWithdrawQueryInput);

}
