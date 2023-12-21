package com.vku.bocuoi.yu.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getCurrentYear() {
        return String.valueOf(LocalDate.now().getYear());
    }

    public static String getCurrentMonth() {
        return formatTwoDigitNumber(LocalDate.now().getMonthValue());
    }

    public static String getCurrentDay() {
        return formatTwoDigitNumber(LocalDate.now().getDayOfMonth());
    }

    private static String formatTwoDigitNumber(int number) {
        return String.format("%02d", number);
    }

    public static String getCurrentYearMonthDayPath() {
        return getCurrentYear() + "/" + getCurrentMonth() + "/" + getCurrentDay();
    }
}