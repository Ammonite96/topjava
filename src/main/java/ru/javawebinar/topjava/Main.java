package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.impl.MealServiceImp;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!");
        System.out.println(" ");

        MealService mealService = new MealServiceImp();

        mealService.getAllMeal().forEach(System.out::println);
        System.out.println("");
        System.out.println(mealService.getMeal(1).toString());
        System.out.println("");
        mealService.createMeal(new Meal(LocalDateTime.of(2021, Month.JANUARY,7,16,42), "diner", 1000));
        mealService.getAllMeal().forEach(System.out::println);
        mealService.updateMeal(15, new Meal(LocalDateTime.of(2021, Month.JANUARY, 7, 16,56), "обед", 200));
        System.out.println("");
        System.out.println(mealService.getMeal(15));
        mealService.deleteMeal(15);
        System.out.println("");
        mealService.getAllMeal().forEach(System.out::println);
    }
}
