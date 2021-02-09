package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.impl.MealServiceImp;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!");
        System.out.println(" ");

        MealService mealService = new MealServiceImp();
        List<Meal> mealsList = mealService.getAllMeal();
        List<MealTo> mealWithExcesses = MealsUtil.filteredByStreams(mealsList, LocalTime.MIN,LocalTime.MAX,2000);

        mealsList.forEach(System.out::println);
        System.out.println("");
        mealWithExcesses.forEach(System.out::println);

        Meal meal = mealService.getMeal(1);
        mealService.updateMeal(1, new Meal(LocalDateTime.MAX, "discription", 2000));

        System.out.println("");
        mealsList.forEach(System.out::println);

    }
}
