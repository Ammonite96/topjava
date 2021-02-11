package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
                new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 0, 0), "Оо в Феврале", 100),
                new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 8, 15), "Оо", 200),
                new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0), "Завтрак", 1500),
                new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 7, 15), "Оо в Апреле", 600),
                new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 11, 15), "Завтрак", 200),
                new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 12, 15), "Обед", 800),
                new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 21, 15), "Ужин", 500)
        );

        List<MealTo> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("-----------------------------------------------------------------");

    }

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
            new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 0, 0), "Оо в Феврале", 100),
            new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 8, 15), "Оо", 200),
            new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0), "Завтрак", 1500),
            new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 7, 15), "Оо в Апреле", 600),
            new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 11, 15), "Завтрак", 200),
            new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 12, 15), "Обед", 800),
            new Meal(LocalDateTime.of(2020, Month.APRIL, 25, 21, 15), "Ужин", 500)
    );

    public static List<MealTo> filteredByCycles(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        
        Map<LocalDate, Integer> caloriesByDay = new HashMap<>();
        for (Meal meal : meals) {
            LocalDateTime localDateTime = meal.getDateTime();
            LocalDate localDate = localDateTime.toLocalDate();

            int calories = meal.getCalories();

            caloriesByDay.merge(localDate, calories, Integer::sum);
        }

        List<MealTo> userMealWithExcesses = new ArrayList<>();
        for (Meal meal : meals) {
            LocalDateTime localDateTime = meal.getDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            LocalTime localTime = localDateTime.toLocalTime();

            boolean excess = caloriesByDay.get(localDate) > caloriesPerDay;

            if (DateTimeUtil.isBetweenHalfOpen(localTime, startTime, endTime)) {
                userMealWithExcesses.add(new MealTo(localDateTime, meal.getDescription(), meal.getCalories(), excess));
            }
        }

        return userMealWithExcesses;
    }

    public static List<MealTo> getTos(Collection<Meal> meals, int caloriesPerDay) {
        return filterByPredicate(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealTo> getFilteredTos(Collection<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime) {
        return filterByPredicate(meals, caloriesPerDay, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime));
    }

    public static List<MealTo> filterByPredicate(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(filter)
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
