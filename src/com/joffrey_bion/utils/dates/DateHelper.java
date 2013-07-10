package com.joffrey_bion.utils.dates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    private static final String DATE = "yyyy-MM-dd";
    private static final String TIME = "HH:mm:ss";
    private static final String MILLIS = ".SSS";
    private static final String DATE_TIME_SEP = " ";

    public static String toDate(long milliseconds) {
        return format(milliseconds, DATE);
    }

    public static String toTime(long milliseconds) {
        return format(milliseconds, TIME);
    }

    public static String toTimeMillis(long milliseconds) {
        return format(milliseconds, TIME + MILLIS);
    }

    public static String toDateTime(long milliseconds) {
        return format(milliseconds, DATE + DATE_TIME_SEP + TIME);
    }

    public static String toDateTimeMillis(long milliseconds) {
        return format(milliseconds, DATE + DATE_TIME_SEP + TIME + MILLIS);
    }

    public static String toFileNameDateTime(long milliseconds) {
        return format(milliseconds, "yyyy.MM.dd-HHmmss");
    }

    public static String format(long milliseconds, String pattern) {
        return new SimpleDateFormat(pattern, Locale.US).format(new Date(milliseconds));
    }

    public static void displayTimestamp(String name, long timestampNanos) {
        System.out.println(name + ": " + toDateTimeMillis(timestampNanos / 1000000));
    }
}
