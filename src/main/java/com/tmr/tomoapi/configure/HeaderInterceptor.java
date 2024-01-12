package com.tmr.tomoapi.configure;

import com.tmr.tomoapi.constant.SecurityConstants;
import com.tmr.tomoapi.domain.Output.UserLoginDTO;
import com.tmr.tomoapi.exception.NotLoginException;
import com.tmr.tomoapi.exception.NotPermissionException;
import com.tmr.tomoapi.security.SecurityContextHolder;
import com.tmr.tomoapi.utils.AuthUtil;
import com.tmr.tomoapi.utils.SecurityUtils;
import com.tmr.tomoapi.utils.ServletUtils;
import com.tmr.tomoapi.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.naming.NoPermissionException;

public class HeaderInterceptor implements AsyncHandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (!(handler instanceof HandlerMethod))
        {
            return true;
        }

        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID));
        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
        SecurityContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));
        SecurityContextHolder.setAppId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_APP_ID));

        String token = SecurityUtils.getToken();
        if (StringUtils.isNotEmpty(token))
        {
            //try{
                UserLoginDTO loginUser = AuthUtil.getLoginUser(token);
                if (StringUtils.isNotNull(loginUser))
                {
                    AuthUtil.verifyLoginUserExpire(loginUser);
                    SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
                }
            /*}catch (Exception e){
                e.printStackTrace();
            }*/

        }
        else {
            throw new NotLoginException("No access token");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        SecurityContextHolder.remove();
    }
}
