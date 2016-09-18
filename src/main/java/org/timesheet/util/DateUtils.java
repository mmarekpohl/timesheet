package org.timesheet.util;


import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate getTomorrow(LocalDate date) {
        return date.plusDays(1);
    }

    private static final String DATE_PATTERN = "yyyy-M-d";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDate getDate(String stringDate) {
        stringDate = StringUtils.substringBefore(stringDate, " ");
        return LocalDate.parse(stringDate, FORMATTER);
    }

    public static String formatDate (LocalDate date) {
        return FORMATTER.format(date);
    }
}
