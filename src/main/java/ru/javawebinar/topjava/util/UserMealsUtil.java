package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
                new UserMeal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 0, 0), "Оо в Феврале", 100),
                new UserMeal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 8, 15), "Оо", 200),
                new UserMeal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0), "Завтрак", 1500),
                new UserMeal(LocalDateTime.of(2020, Month.APRIL, 25, 7, 15), "Оо в Апреле", 600),
                new UserMeal(LocalDateTime.of(2020, Month.APRIL, 25, 11, 15), "Завтрак", 200),
                new UserMeal(LocalDateTime.of(2020, Month.APRIL, 25, 12, 15), "Обед", 800),
                new UserMeal(LocalDateTime.of(2020, Month.APRIL, 25, 21, 15), "Ужин", 400)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("-----------------------------------------------------------------");

        List<UserMealWithExcess> mealsToStream = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsToStream.forEach(System.out::println);

        //System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        Map<LocalDate, Integer> dateIntegerMap = new TreeMap<>();

        for (UserMeal meal : meals) {
            LocalDateTime localDateTime = meal.getDateTime(); // give date
            LocalDate localDate = localDateTime.toLocalDate(); // for put of Map

            int calories = meal.getCalories();

            dateIntegerMap.merge(localDate, calories, Integer::sum);
        }

        for (UserMeal meal : meals) {
            LocalDateTime localDateTime = meal.getDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            LocalTime localTime = localDateTime.toLocalTime();

            boolean excess = dateIntegerMap.get(localDate) <= caloriesPerDay;

            if (TimeUtil.isBetweenHalfOpen(localTime, startTime, endTime)) {
                userMealWithExcesses.add(new UserMealWithExcess(localDateTime, meal.getDescription(), meal.getCalories(), excess));
            }
        }

        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dateIntegerMap = meals.stream().
                collect(Collectors.groupingBy(o -> o.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> {
                    boolean excess = dateIntegerMap.get(userMeal.getDateTime().toLocalDate()) <= caloriesPerDay;
                    return new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), excess);
                })
                .collect(Collectors.toList());
    }
}
