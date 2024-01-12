package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppDepositInput {
    @NotNull(message = "Crypto is null")
    private String crypto;

    @NotNull(message = "USDT value is null")
    private String usdtValue;
}
