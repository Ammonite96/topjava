package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.impl.MealServiceImp;

import java.util.List;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!");

        MealService mealService = new MealServiceImp();

        List<Meal> meals = mealService.getAllMeal();
        meals.forEach(System.out::println);

        System.out.println("");
        mealService.deleteMeal(4);
        meals.forEach(System.out::println);
    }
}
