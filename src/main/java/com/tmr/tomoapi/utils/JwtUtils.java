package com.tmr.tomoapi.utils;

import com.tmr.tomoapi.constant.SecurityConstants;
import com.tmr.tomoapi.constant.TokenConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.util.Map;

public class JwtUtils
{
    public static String secret = TokenConstants.SECRET;

    public static String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
        return token;
    }

    public static Claims parseToken(String token)
    {
        return Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getBody();
    }

    public static String getUserKey(String token)
    {
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    public static String getUserKey(Claims claims)
    {
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    public static String getUserId(String token)
    {
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
    }

    public static String getUserId(Claims claims)
    {
        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
    }

    public static String getUserName(String token)
    {
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    public static String getAppId(Claims claims)
    {
        return getValue(claims, SecurityConstants.DETAILS_APP_ID);
    }


    public static void main(String[] args) {
        System.out.println(getUserId("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VydmVyc2lvbiI6IkVPSjkwWTU1IiwidXNlcl9pZCI6IjgxMTk5OWU1MmYxYTRjZGZiYzAwMzE3ZWYzNzM4MmJmIiwidXNlcl9rZXkiOiJhYzBkM2E2My05MDg3LTQ2OGQtOTk5ZC1mYzJhZDc3ZDk5YTMiLCJ1c2VybmFtZSI6InRlc3Rfd2x6In0.ARW6I3YfBvNnI3qn6bPkQNw6GpobrcB-GxPE1h5lYp9-axl-rEa5-CAMTma9HekPQUm7LuKrNjdVwyfnBNOT7Q"));
    }

    public static String getUserName(Claims claims)
    {
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    public static String getValue(Claims claims, String key)
    {
        return Convert.toStr(claims.get(key), "");
    }

    public static String getUserVersion(Claims claims)
    {
        return getValue(claims, SecurityConstants.DETAILS_USERVERSION);
    }

    public static String getUserVersionByToken(String token)
    {
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USERVERSION);
    }
}