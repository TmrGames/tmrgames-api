package com.tmr.tomoapi.service;

import com.tmr.tomoapi.domain.Input.AppDepositExternalInput;
import com.tmr.tomoapi.domain.Input.AppDepositInput;
import com.tmr.tomoapi.domain.Input.AppLoginInput;
import com.tmr.tomoapi.domain.Input.AppTmrDepositInput;
import com.tmr.tomoapi.domain.Output.AppLoginDTO;
import com.tmr.tomoapi.domain.Output.AppUserInfoDTO;

public interface AppLoginService {
    AppLoginDTO getTmrBetUrl();

    String getTmrAuthToken();

    String getTmrBalance(AppLoginInput appLoginInput);

    String getTmrBetRate();

    String depositTmrBet(AppTmrDepositInput appTmrDepositInput);

    String withdrawTmrBet(AppTmrDepositInput appTmrDepositInput);

    AppLoginDTO getOceanxUrl();

    AppUserInfoDTO getOceanxUserInfo();

    AppUserInfoDTO depositOceanx(AppDepositInput appDepositInput);

    AppUserInfoDTO depositOceanxExternal(AppDepositExternalInput appDepositExternalInput);
}
