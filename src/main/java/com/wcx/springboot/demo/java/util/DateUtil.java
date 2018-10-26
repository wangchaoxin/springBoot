package com.wcx.springboot.demo.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    public static Date strToData(String date) {
        try {
            return DEFAULT_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date strToData(String date, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToStr(Date date) {
        return DEFAULT_DATE_FORMAT.format(date);
    }

    public static String dateToStr(Date date, SimpleDateFormat simpleDateFormat) {
        if (simpleDateFormat == null)
            return "";
        return simpleDateFormat.format(date);
    }
}
