package com.tmr.tomoapi.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    public static final String[] excludeUrls = { "/user/register","/user/login", "/user/logout", "/refresh", "/user/sendEmailCode", "/app/depositOceanxExternal" };

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(getHeaderInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrls)
                .order(-10);
    }

    public HeaderInterceptor getHeaderInterceptor()
    {
        return new HeaderInterceptor();
    }
}