package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserWithdrawInput {
    @NotNull(message = "Crypto is null")
    private String crypto;

    @NotNull(message = "Amount is null")
    private String amount;

    @NotNull(message = "To address is null")
    private String toAddress;
}
