package com.tmr.tomoapi.utils;

import com.tmr.tomoapi.annotation.RequiresPermissions;
import com.tmr.tomoapi.annotation.RequiresRoles;
import com.tmr.tomoapi.domain.Output.UserLoginDTO;
import com.tmr.tomoapi.security.AuthLogic;

public class AuthUtil
{
    public static AuthLogic authLogic = new AuthLogic();

    public static void logout()
    {
        authLogic.logout();
    }

    public static void logoutByToken(String token)
    {
        authLogic.logoutByToken(token);
    }

    public static void checkLogin()
    {
        authLogic.checkLogin();
    }

    public static UserLoginDTO getLoginUser(String token) throws Exception {
        return authLogic.getLoginUser(token);
    }

    public static void verifyLoginUserExpire(UserLoginDTO userLoginDTO)
    {
        authLogic.verifyLoginUserExpire(userLoginDTO);
    }

    public static boolean hasRole(String role)
    {
        return authLogic.hasRole(role);
    }

    public static void checkRole(String role)
    {
        authLogic.checkRole(role);
    }

    public static void checkRole(RequiresRoles requiresRoles)
    {
        authLogic.checkRole(requiresRoles);
    }

    public static void checkRoleAnd(String... roles)
    {
        authLogic.checkRoleAnd(roles);
    }

    public static void checkRoleOr(String... roles)
    {
        authLogic.checkRoleOr(roles);
    }

    public static boolean hasPermi(String permission)
    {
        return authLogic.hasPermi(permission);
    }

    public static void checkPermi(String permission)
    {
        authLogic.checkPermi(permission);
    }

    public static void checkPermi(RequiresPermissions requiresPermissions)
    {
        authLogic.checkPermi(requiresPermissions);
    }

    public static void checkPermiAnd(String... permissions)
    {
        authLogic.checkPermiAnd(permissions);
    }

    public static void checkPermiOr(String... permissions)
    {
        authLogic.checkPermiOr(permissions);
    }
}