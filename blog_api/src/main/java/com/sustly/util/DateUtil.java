package com.sustly.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyue
 * @date 2019/4/4 11:20
 */
public class DateUtil {

    /**
     * 获取当前时间 格式yyyy-MM-dd HH:mm:ss
     * @return LocalTime
     */
    public static String getLocalTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}