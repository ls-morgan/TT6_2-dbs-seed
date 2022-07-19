package com.dbs.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateTimeUtil {

    public static final String DD_MMM_YY = "dd-MMM-yy";
    public static final String DD_MMM_YY_hh_mm = "dd-MMM-yy hh:mm";

    public static String convertStringToDateFormat(String dateString, String inputDateFormat, String outputDateFormat) {
        if (ObjectUtils.isEmpty(dateString)) return null;
        try {
            Date date = new SimpleDateFormat(inputDateFormat).parse(dateString);
            return DateFormatUtils.format(date, outputDateFormat);
        } catch (Exception e) {
            log.error("Convert date format error {}, {}", dateString, e.getMessage());
        }
        return null;
    }

    public static String convertStringToDateMonthYear(String dateString, String inputDateFormat) {
        if (ObjectUtils.isEmpty(dateString)) return null;

        try {
            return DateFormatUtils.format(new SimpleDateFormat(inputDateFormat).parse(dateString), DD_MMM_YY);
        } catch (Exception e) {
            log.error("Convert date format error {}, {}", dateString, e.getMessage());
        }
        return null;
    }

    public static String convertStringToDateMonthYearHourMinutes(String dateString, String inputDateFormat) {
        if (ObjectUtils.isEmpty(dateString)) return null;

        try {
            Date date = new SimpleDateFormat(inputDateFormat).parse(dateString);
            return DateFormatUtils.format(date, DD_MMM_YY_hh_mm);
        } catch (Exception e) {
            log.error("Convert date format error {}, {}", dateString, e.getMessage());
        }
        return null;
    }
}
