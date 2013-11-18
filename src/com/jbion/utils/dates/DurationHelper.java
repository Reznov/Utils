package com.jbion.utils.dates;

import java.text.ParseException;

public class DurationHelper {

    private static long getZero() {
        try {
            return DateHelper.timestampStrToMillis("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            System.err.println("Internal error.");
            return -1;
        }
    }

    public static String format(long milliseconds, String pattern) {
        return DateHelper.format(getZero() + milliseconds, pattern);
    }

    public static String toTime(long milliseconds) {
        return format(milliseconds, "HH:mm:ss");
    }

    public static String toTimeMillis(long milliseconds) {
        return format(milliseconds, "HH:mm:ss.SSS");
    }

    public static long strToMillis(String duration, String timeFormat) throws ParseException {
        long zero = getZero();
        return DateHelper.timestampStrToMillis("1970-01-01 " + duration, "yyyy-MM-dd "
                + timeFormat) - zero;
    }
}
