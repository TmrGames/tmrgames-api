package com.tmr.tomoapi.controller;

import com.tmr.tomoapi.domain.Input.*;
import com.tmr.tomoapi.domain.Output.AppLoginDTO;
import com.tmr.tomoapi.domain.Output.AppUserInfoDTO;
import com.tmr.tomoapi.domain.Output.UserLoginDTO;
import com.tmr.tomoapi.domain.R;
import com.tmr.tomoapi.service.AppLoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/app")
public class AppLoginController {

    @Autowired
    private AppLoginService appLoginService;

    private static final Logger logger = LoggerFactory.getLogger(AppLoginController.class);

    @PostMapping("/getTmrBetUrl")
    public R<AppLoginDTO> getTmrBetUrl() {
        AppLoginDTO appLoginDTO = appLoginService.getTmrBetUrl();
        return R.ok(appLoginDTO);
    }

    @PostMapping("/getTmrBetToken")
    public R<String> getTmrBetToken(){
        String token = appLoginService.getTmrAuthToken();
        if(Objects.isNull(token)){
            return R.fail();
        }
        return R.ok(appLoginService.getTmrAuthToken());
    }

    @PostMapping("/getTmrBetBalance")
    public R<String> getTmrBetBalance(@Valid @RequestBody AppLoginInput appLoginInput){
        return R.ok(appLoginService.getTmrBalance(appLoginInput));
    }

    @GetMapping("/getTmrBetRate")
    public R<String> getTmrBetRate(){
        return R.ok(appLoginService.getTmrBetRate());
    }

    @PostMapping("/depositTmrBet")
    public R<String> depositTmrBet(@Valid @RequestBody AppTmrDepositInput appTmrDepositInput){
        return R.ok(appLoginService.depositTmrBet(appTmrDepositInput));
    }

    @PostMapping("/withdrawTmrBet")
    public R<String> withdrawTmrBet(@Valid @RequestBody AppTmrDepositInput appTmrDepositInput){
        return R.ok(appLoginService.withdrawTmrBet(appTmrDepositInput));
    }

    @PostMapping("/getOceanxUrl")
    public R<AppLoginDTO> getOceanxUrl() {
        AppLoginDTO appLoginDTO = appLoginService.getOceanxUrl();
        return R.ok(appLoginDTO);
    }

    @PostMapping("/getOceanxUserInfo")
    public R<AppUserInfoDTO> getOceanxUserInfo() {
        AppUserInfoDTO appUserInfoDTO = appLoginService.getOceanxUserInfo();
        return R.ok(appUserInfoDTO);
    }

    @PostMapping("/depositOceanx")
    public R<AppUserInfoDTO> depositOceanx(@Valid @RequestBody AppDepositInput appDepositInput) {
        AppUserInfoDTO appUserInfoDTO = appLoginService.depositOceanx(appDepositInput);
        return R.ok(appUserInfoDTO);
    }

    @PostMapping("/depositOceanxExternal")
    public R<AppUserInfoDTO> depositOceanxExternal(@Valid @RequestBody AppDepositExternalInput appDepositExternalInput) {
        AppUserInfoDTO appUserInfoDTO = appLoginService.depositOceanxExternal(appDepositExternalInput);
        return R.ok(appUserInfoDTO);
    }
}
