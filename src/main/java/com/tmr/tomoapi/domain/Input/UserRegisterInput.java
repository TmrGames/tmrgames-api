package com.tmr.tomoapi.domain.Input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterInput {
    @NotNull(message = "Login name is null")
    @Email(message = "Login name is not email")
    private String loginName;

    @NotNull(message = "Password is null")
    private String password;

    @NotNull(message = "Country is null")
    private String country;

    @NotNull(message = "Captcha is null")
    private String captcha;
}
