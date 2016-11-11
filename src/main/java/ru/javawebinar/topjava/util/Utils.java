package ru.javawebinar.topjava.util;


public class Utils {
    public static String getStringWithFirstCharAtUpperCase(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String caloriesForMealIsAStringException(String str) {
        return str.contains("convert") ? "must be a number" : str;
    }
}
