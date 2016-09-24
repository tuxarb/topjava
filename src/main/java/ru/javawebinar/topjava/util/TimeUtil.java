package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static final LocalDate MIN_DATE = LocalDate.of(0, 0 , 0);
    public static final LocalDate MAX_DATE = LocalDate.of(2500, 0 , 0);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");

    public static <T extends Comparable<? super T>> boolean isBetween(T currentTime, T startTime, T endTime) {
        return currentTime.compareTo(startTime) >= 0 && currentTime.compareTo(endTime) <= 0;
    }

    public static String toString(LocalDateTime ldt)
    {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

}
