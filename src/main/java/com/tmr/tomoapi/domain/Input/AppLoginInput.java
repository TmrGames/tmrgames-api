package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppLoginInput {
    @NotNull(message = "Token is null")
    private String token;
}
