package edu.spring.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private TimeUtil() {}

    /**
     * Get current date and time using SimpleDateFormat
     * @return a string containing date and time
     */
    public static String getSdfCurrentDateTime() {
        final String FORMAT = "dd/MM/yyyy HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(new Date());
    }

    /**
     * Get current date and time using SimpleDateFormat
     * @param format format to use, see javadoc of SimpleDateFormat
     * @return a string containing date and time
     */
    public static String getSdfCurrentDateTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
}
