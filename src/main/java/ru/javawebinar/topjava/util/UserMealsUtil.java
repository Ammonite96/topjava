package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
                new UserMeal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 10,0), "Завтрак", 1500)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        Map<LocalDate, Integer> dateIntegerMap = new TreeMap<>();

        int fullCalories = 0;

        for (UserMeal meal : meals) {
            LocalDateTime localDateTime = meal.getDateTime(); // give date
            LocalDate localDate = localDateTime.toLocalDate(); // for put of Map
            LocalTime localTime = localDateTime.toLocalTime();
            LocalTime time = LocalTime.of(0, 0,0,0);

            int calories = meal.getCalories();

            if (localTime.toString().equals(time.toString())) {
                fullCalories = calories;
            }

            fullCalories += calories;

            dateIntegerMap.put(localDate, fullCalories);
        }

        for (UserMeal meal :  meals) {
            LocalDateTime localDateTime = meal.getDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            LocalTime localTime = localDateTime.toLocalTime();

            boolean excess;

            if (dateIntegerMap.get(localDate) > caloriesPerDay){
                excess = false;
            } else {
                excess = true;
            }

            if (TimeUtil.isBetweenHalfOpen(localTime, startTime, endTime)) {
                userMealWithExcesses.add(new UserMealWithExcess(localDateTime, meal.getDescription(), meal.getCalories(), excess));
            }
        }

        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
