package com.tmr.tomoapi.service;

import com.tmr.tomoapi.constant.CacheConstants;
import com.tmr.tomoapi.constant.SecurityConstants;
import com.tmr.tomoapi.constant.TokenConstants;
import com.tmr.tomoapi.domain.Input.UserLoginInput;
import com.tmr.tomoapi.domain.Output.UserLoginDTO;
import com.tmr.tomoapi.utils.IdUtils;
import com.tmr.tomoapi.utils.JwtUtils;
import com.tmr.tomoapi.utils.RandomUtil;
import com.tmr.tomoapi.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TokenService{

    @Autowired
    private RedisService redisService;
    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    public Map<String, Object> createToken(UserLoginDTO userLoginDTO)
    {
        String token = IdUtils.fastUUID();
        Long userId = userLoginDTO.getUserId();
        String userName = userLoginDTO.getLoginName();
        userLoginDTO.setToken(token);
        String version = RandomUtil.getRandomString(SecurityConstants.LOGIN_VERSION_LENGTH);
        userLoginDTO.setVersion(version);
        refreshToken(userLoginDTO);

        redisService.setCacheObject( SecurityConstants.VERSION_PRE + userId, version, CacheConstants.EXPIRATION, TimeUnit.MINUTES);

        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);
        claimsMap.put(SecurityConstants.DETAILS_USERVERSION, version);

        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in", CacheConstants.EXPIRATION);
        return rspMap;
    }

    public void refreshToken(UserLoginDTO userLoginDTO)
    {
        userLoginDTO.setLoginTime(System.currentTimeMillis());
        userLoginDTO.setExpireTime(userLoginDTO.getLoginTime() + CacheConstants.EXPIRATION * MILLIS_MINUTE);
        String userKey = getTokenKey(userLoginDTO.getToken());
        redisService.setCacheObject(userKey, userLoginDTO, CacheConstants.EXPIRATION, TimeUnit.MINUTES);
    }

    private String getTokenKey(String token)
    {
        return ACCESS_TOKEN + token;
    }

    public UserLoginDTO getLoginUser(String token) throws Exception {
        UserLoginDTO user = null;
        try
        {
            if (StringUtils.isNotEmpty(token))
            {
                if(token.startsWith(TokenConstants.APPPREFIX)){
                    token = token.replace(TokenConstants.APPPREFIX,StringUtils.EMPTY);
                }
                String userkey = JwtUtils.getUserKey(token);
                user = redisService.getCacheObject(getTokenKey(userkey));
                return user;
            }
        }
        catch (Exception e)
        {
            throw new Exception("Get token" + token + "failed");
        }
        return user;
    }

    public void delLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userkey = JwtUtils.getUserKey(token);
            redisService.deleteObject(getTokenKey(userkey));
        }
    }

    public void verifyToken(UserLoginDTO userLoginDTO)
    {
        long expireTime = userLoginDTO.getExpireTime();
        long currentTime = System.currentTimeMillis();
        long millisMinuteTen = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;
        if (expireTime - currentTime <= millisMinuteTen)
        {
            refreshToken(userLoginDTO);
        }
    }
}