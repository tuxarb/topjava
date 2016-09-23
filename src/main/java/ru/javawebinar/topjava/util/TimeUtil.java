package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");

    public static <T extends Comparable<? super T>> boolean isBetween(T currentTime, T startTime, T endTime) {
        return currentTime.compareTo(startTime) >= 0 && currentTime.compareTo(endTime) <= 0;
    }

    public static String toString(LocalDateTime ldt)
    {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

}
