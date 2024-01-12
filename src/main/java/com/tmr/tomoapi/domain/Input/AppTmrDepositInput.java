package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppTmrDepositInput {
    @NotNull(message = "Crypto is null")
    private String crypto;

    @NotNull(message = "Token is null")
    private String token;

    @NotNull(message = "USDT value is null")
    private String usdtValue;
}
