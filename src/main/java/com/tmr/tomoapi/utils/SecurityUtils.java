package com.tmr.tomoapi.utils;

import com.tmr.tomoapi.constant.Constants;
import com.tmr.tomoapi.constant.SecurityConstants;
import com.tmr.tomoapi.constant.TokenConstants;
import com.tmr.tomoapi.domain.Output.UserLoginDTO;
import com.tmr.tomoapi.security.SecurityContextHolder;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtils
{
    public static String getUserId()
    {
        return SecurityContextHolder.getUserId();
    }

    public static String getUsername()
    {
        return SecurityContextHolder.getUserName();
    }

    public static String getUserKey()
    {
        return SecurityContextHolder.getUserKey();
    }

    public static UserLoginDTO getLoginUser()
    {
        return SecurityContextHolder.get(SecurityConstants.LOGIN_USER, UserLoginDTO.class);
    }

    public static String getToken()
    {
        return getToken(ServletUtils.getRequest());
    }

    public static String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    public static String replaceTokenPrefix(String token)
    {
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.APPPREFIX))
        {
            token = token.replaceFirst(TokenConstants.APPPREFIX, "");
        }
        return token;
    }

    public static boolean isAdmin(String userId)
    {
        return userId != null && getLoginUser().getRoles().contains( Constants.SYS_ADMIN );
    }

    /*public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("e10adc3949ba59abbe56e057f20f883e"));
        System.out.println(matchesPassword("e10adc3949ba59abbe56e057f20f883e",encryptPassword("e10adc3949ba59abbe56e057f20f883e")));
    }*/
}
