package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserWithdrawQueryInput {
    @NotNull(message = "Crypto is null")
    private String crypto;
}
