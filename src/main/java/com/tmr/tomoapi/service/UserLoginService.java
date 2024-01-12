package com.tmr.tomoapi.service;

import com.tmr.tomoapi.domain.Input.SendCodeInput;
import com.tmr.tomoapi.domain.Input.UserAccountInput;
import com.tmr.tomoapi.domain.Input.UserLoginInput;
import com.tmr.tomoapi.domain.Input.UserRegisterInput;
import com.tmr.tomoapi.domain.Output.UserLoginDTO;
import com.tmr.tomoapi.entity.SysUser;

public interface UserLoginService {
    Boolean register(UserRegisterInput userRegisterInput);

    UserLoginDTO login(UserLoginInput userLoginInput);

    Boolean sendMailCode(String email);
}
