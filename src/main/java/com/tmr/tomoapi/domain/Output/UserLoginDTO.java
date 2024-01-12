package com.tmr.tomoapi.domain.Output;

import lombok.Data;

import java.util.Set;

@Data
public class UserLoginDTO {
    private String loginName;
    private String email;
    private String phone;
    private String country;
    private Long userId;
    private String token;
    private Long loginTime;
    private Long expireTime;
    private Set<String> permissions;
    private Set<String> roles;
    private String version;
}
