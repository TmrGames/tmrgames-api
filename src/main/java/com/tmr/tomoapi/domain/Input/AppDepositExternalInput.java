package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppDepositExternalInput {
    @NotNull(message = "Crypto is null")
    private String crypto;

    @NotNull(message = "USDT value is null")
    private String usdtValue;

    @NotNull(message = "Login name is null")
    private String loginName;

    @NotNull(message = "Timestamp is null")
    private String timestamp;

    @NotNull(message = "Signature value is null")
    private String signature;
}
