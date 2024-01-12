package com.tmr.tomoapi.controller;

import com.tmr.tomoapi.domain.Input.UserAccountInput;
import com.tmr.tomoapi.domain.Input.UserTicketInput;
import com.tmr.tomoapi.domain.Input.UserWithdrawInput;
import com.tmr.tomoapi.domain.Input.UserWithdrawQueryInput;
import com.tmr.tomoapi.domain.Output.UserAccountDTO;
import com.tmr.tomoapi.domain.Output.UserTicketDTO;
import com.tmr.tomoapi.domain.Output.UserWithdrawDTO;
import com.tmr.tomoapi.domain.R;
import com.tmr.tomoapi.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/getBalance")
    public R<UserAccountDTO> getBalance(@Valid @RequestBody UserAccountInput userAccountInput) {
        UserAccountDTO userAccountDTO = userAccountService.getBalance(userAccountInput);
        return R.ok(userAccountDTO);
    }

    @PostMapping("/buyTicket")
    public R<UserTicketDTO> buyTicket(@Valid @RequestBody UserTicketInput userTicketInput) {
        UserTicketDTO userTicketDTO = userAccountService.buyTicket(userTicketInput);
        return R.ok(userTicketDTO);
    }

    @PostMapping("/withdraw")
    public R<Void> withdraw(@Valid @RequestBody UserWithdrawInput userWithdrawInput) {
        Boolean ret = userAccountService.withdraw(userWithdrawInput);
        if(ret){
            return R.ok();
        }
        return R.fail();
    }

    @PostMapping("/getWithdraw")
    public R<UserWithdrawDTO> getWithdraw(@Valid @RequestBody UserWithdrawQueryInput userWithdrawQueryInput) {
        UserWithdrawDTO userWithdrawDTO = userAccountService.getWithdraw(userWithdrawQueryInput);
        return R.ok(userWithdrawDTO);
    }
}
