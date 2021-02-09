package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    Meal getMeal(int id);

    List<Meal> getAllMeal();

    void createMeal(Meal meal);

    void updateMeal(int id, Meal meal);

    void deleteMeal(Integer id);
}
