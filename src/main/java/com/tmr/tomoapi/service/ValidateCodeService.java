package com.tmr.tomoapi.service;

import com.tmr.tomoapi.domain.AjaxResult;
import com.tmr.tomoapi.exception.CaptchaException;

import java.io.IOException;

public interface ValidateCodeService
{
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    public void checkCaptcha(String key, String value) throws CaptchaException;
}
