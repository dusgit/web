package com.yinrun.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    public static Integer getCurrentTime()
    {
        Long currentTime = System.currentTimeMillis() / 1000;
        return currentTime.intValue();
    }

    public static String getFormatDateForNet(Integer time)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.DD");
            return sdf.format(new Date(time * 1000));
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
