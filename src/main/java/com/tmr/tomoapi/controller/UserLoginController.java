package com.tmr.tomoapi.controller;

import com.tmr.tomoapi.domain.AjaxResult;
import com.tmr.tomoapi.domain.Input.SendCodeInput;
import com.tmr.tomoapi.domain.Input.UserAccountInput;
import com.tmr.tomoapi.domain.Input.UserLoginInput;
import com.tmr.tomoapi.domain.Input.UserRegisterInput;
import com.tmr.tomoapi.domain.Output.UserAccountDTO;
import com.tmr.tomoapi.domain.Output.UserLoginDTO;
import com.tmr.tomoapi.domain.R;
import com.tmr.tomoapi.entity.SysUser;
import com.tmr.tomoapi.exception.CaptchaException;
import com.tmr.tomoapi.mapper.UserLoginMapper;
import com.tmr.tomoapi.service.TokenService;
import com.tmr.tomoapi.service.UserLoginService;
import com.tmr.tomoapi.service.ValidateCodeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private TokenService tokenService;

    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    @PostMapping("/login")
    public R<UserLoginDTO> login(@Valid @RequestBody UserLoginInput userLoginInput) {
        UserLoginDTO userLoginDTO = userLoginService.login(userLoginInput);
        return R.ok(userLoginDTO);
    }

    @PostMapping("/register")
    public R<Void> register(@Valid @RequestBody UserRegisterInput userRegisterInput) {
        Boolean ret = userLoginService.register(userRegisterInput);
        if(ret){
            return R.ok();
        }
        return R.fail();
    }

    @PostMapping("/sendEmailCode")
    public R<Void> sendEmailCode(@RequestBody SendCodeInput sendCodeInput) {
        if(userLoginService.sendMailCode(sendCodeInput.getEmail())){
            return R.ok();
        }
        return R.fail();
    }
}
