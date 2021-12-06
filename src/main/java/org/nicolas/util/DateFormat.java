package org.nicolas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zorth
 */
public class DateFormat {
    private static final String YYYY = "yyyy";

    private static final String YYYY_MM = "yyyy-MM";

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    private static final String YYYY_MM_DD_HH_MM = "yyyyMMddHHmm";

    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String MM_DD_HH_MM = "MM-dd HH:mm";

    private String SimpleDateFormat(String format, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public String YMDDateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        return sdf.format(date);
    }

    /**
     * get formatted current date
     * @return String current time
     */
    public static String getCurrentTime() {
        // 获取当前时间
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String time = sdf.format(date);
        return time;
    }
}
