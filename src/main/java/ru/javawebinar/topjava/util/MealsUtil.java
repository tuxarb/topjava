package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public final static List<Meal> MEAL_LIST = Arrays.asList(
            new Meal(LocalDateTime.of(2016, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2016, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2016, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2016, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2016, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2016, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static void main(String[] args) {
        List<MealWithExceed> mealWithExceeds = getFilteredWithExceeded(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(21, 0), DEFAULT_CALORIES_PER_DAY).stream()
                .collect(Collectors.toList());
        mealWithExceeds.forEach(System.out::println);
    }

    public static List<MealWithExceed> getListWithExceed(Collection<Meal> meals, int caloriesPerDay) {
        return getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
}