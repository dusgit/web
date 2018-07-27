package com.yinrun.utils;

import java.util.UUID;

public class StringUtil
{
    /**
     * 判断字符串为空
     */
    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str);
    }

    /*
     * 判断字符串不为空
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    public static String getToken()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getSalt()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
