package com.tmr.tomoapi.service.impl;

import com.tmr.tomoapi.constant.Constants;
import com.tmr.tomoapi.domain.AjaxResult;
import com.tmr.tomoapi.exception.CaptchaException;
import com.tmr.tomoapi.service.RedisService;
import com.tmr.tomoapi.service.ValidateCodeService;
import com.tmr.tomoapi.utils.CaptchaUtils;
import com.tmr.tomoapi.utils.IdUtils;
import com.tmr.tomoapi.utils.StringUtils;
import io.netty.handler.codec.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService
{

    @Autowired
    private RedisService redisService;

    @Override
    public AjaxResult createCaptcha() throws IOException, CaptchaException
    {
        AjaxResult ajax = AjaxResult.success();

        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String capStr = null, code = null;
        BufferedImage image = null;
        code = CaptchaUtils.getRandomIntCode();
        redisService.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return ajax;
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCaptcha(String code, String uuid) throws CaptchaException
    {
        if (StringUtils.isEmpty(code))
        {
            throw new CaptchaException("Captcha is null");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        if (StringUtils.isEmpty(captcha))
        {
            throw new CaptchaException("Captcha expired");
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException("Captcha not match");
        }
    }

}
