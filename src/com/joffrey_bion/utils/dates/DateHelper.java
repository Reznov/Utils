package com.joffrey_bion.utils.dates;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A helper class to convert timestamps to formatted readable dates.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class DateHelper {

    private static final String DATE = "yyyy-MM-dd";
    private static final String DATE_TIME_SEP = " ";
    private static final String TIME = "HH:mm:ss";
    private static final String MILLIS = ".SSS";

    public static String format(long milliseconds, String pattern) {
        return new SimpleDateFormat(pattern, Locale.US).format(new Date(milliseconds));
    }

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

    public static void displayTimestamp(String name, long timestampNanos) {
        System.out.println(name + ": " + toDateTimeMillis(timestampNanos / 1000000));
    }

    /**
     * Parses the specified timestamp and returns its milliseconds value.
     * 
     * @param timestamp
     *            The timestamp {@code String} to parse.
     * @param formatPattern
     *            The expected format for the specified {@code timestamp}.
     * @return The value of the timestamp in milliseconds.
     * @throws ParseException
     *             If the timestamp does not respect the specified format.
     */
    public static long timestampStrToMillis(String timestamp, String formatPattern)
            throws ParseException {
        DateFormat df = new SimpleDateFormat(formatPattern);
        df.setLenient(false);
        Date date;
        try {
            date = df.parse(timestamp);
        } catch (ParseException e) {
            throw new ParseException("Cannot parse timestamp '" + timestamp
                    + "', expected format: " + formatPattern, e.getErrorOffset());
        }
        return date.getTime();
    }
    
    /**
     * Parses the specified timestamp and returns its nanoseconds value.
     * 
     * @param timestamp
     *            The timestamp {@code String} to parse.
     * @param formatPattern
     *            The expected format for the specified {@code timestamp}.
     * @return The value of the timestamp in nanoseconds.
     * @throws ParseException
     *             If the timestamp does not respect the specified format.
     */
    public static long timestampStrToNanos(String timestamp, String formatPattern)
            throws ParseException {
        return timestampStrToMillis(timestamp, formatPattern) * 1000000;
    }
}
