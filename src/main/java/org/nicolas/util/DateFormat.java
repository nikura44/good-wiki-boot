package org.nicolas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
