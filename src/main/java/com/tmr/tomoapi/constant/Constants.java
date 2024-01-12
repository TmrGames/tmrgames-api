package com.tmr.tomoapi.constant;

public class Constants {

    public static final String UTF8 = "UTF-8";

    public static final String GBK = "GBK";

    public static final String LOOKUP_RMI = "rmi:";

    public static final String LOOKUP_LDAP = "ldap:";

    public static final String LOOKUP_LDAPS = "ldaps:";

    public static final String HTTP = "http://";

    public static final String HTTPS = "https://";

    public static final Integer HTTP_STATUS_SUCCESS = 200;

    public static final Integer HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    public static final String LOGIN_SUCCESS_STATUS = "0";

    public static final String LOGIN_FAIL_STATUS = "1";

    public static final String LOGIN_SUCCESS = "Success";

    public static final String LOGOUT = "Logout";

    public static final String REGISTER = "Register";

    public static final String LOGIN_FAIL = "Error";

    public static final String PAGE_NUM = "pageNum";

    public static final String PAGE_SIZE = "pageSize";

    public static final String ORDER_BY_COLUMN = "orderByColumn";

    public static final String IS_ASC = "isAsc";

    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    public static final String PHONE_MAIL_CODE_KEY = "phone_mail_codes:";

    public static final String RESET_PWD_CODE_KEY = "reset_pwd_codes:";

    public static final long CAPTCHA_EXPIRATION = 2;

    public static final long PHONE_EXPIRATION = 5;

    public static final String SYS_CONFIG_KEY = "sys_config:";

    public static final String SYS_DICT_KEY = "sys_dict:";

    public static final String SYS_TAG_KEY = "sys_tag:";

    public static final String RESOURCE_PREFIX = "/profile";

    public static final String[] JOB_WHITELIST_STR = {"com.icp"};

    public static final String[] JOB_ERROR_STR = {"java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
            "org.springframework", "org.apache", "com.icp.common.core.utils.file"};

    public static final String SYS_ADMIN = "sys_admin";

    public static final String GROUP_ADMIN = "group_admin";

    public static final String OPERATING_PLATFORM = "1";

    public static final String TAG_IS_GLOBAL = "1";

    public static final int FAIL_COUNT = 0;

    public static final String APP_BUCK_NAME = "company-logo";

    public static final String TAG_BUCK_NAME = "tag-logo";

    public static final String DESKTOP_TYPE_MENU = "0";

    public static final String DESKTOP_TYPE_PAGE = "1";

    public static final String JD_LOGIN_TOKEN = "jd_login_token:";

    public static final Integer ALGO_TYPE_HSHA256 = 2;

    public static final Integer ALGO_TYPE_MD5 = 1;

    public static final String NOT_IN_SUB_DEPT = "0";
}
