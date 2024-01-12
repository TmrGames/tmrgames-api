package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginInput {
    @NotNull(message = "Login name is null")
    @Email(message = "Login name is not email")
    private String loginName;

    @NotNull(message = "Password is null")
    private String password;
}
