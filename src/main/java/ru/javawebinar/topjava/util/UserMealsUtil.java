package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0),
                LocalTime.of(12, 0), 2000);
        filteredMealsWithExceeded.forEach(System.out::println);
    }

   /* public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();

        for (UserMeal userMeal : mealList) {
            LocalDate timeForCurrentMeal = userMeal.getDateTime().toLocalDate();

            if (!caloriesSumByDate.containsKey(timeForCurrentMeal)) {
                caloriesSumByDate.put(timeForCurrentMeal, userMeal.getCalories());
                continue;
            }
            int currentCaloriesByDay = caloriesSumByDate.get(timeForCurrentMeal);
            caloriesSumByDate.computeIfPresent(timeForCurrentMeal, (k, v) -> v + currentCaloriesByDay);
        }

        for (UserMeal userMeal : mealList) {
            boolean isExceed = false;
            if (!TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                continue;

            LocalDate timeForCurrentMeal = userMeal.getDateTime().toLocalDate();
            if (caloriesSumByDate.containsKey(timeForCurrentMeal) &&
                    caloriesSumByDate.get(timeForCurrentMeal) > caloriesPerDay)
                isExceed = true;

            result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), isExceed));
        }
        return result;
    }*/

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesByDay = mealList.stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
                        sumCaloriesByDay.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}