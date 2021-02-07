package ru.javawebinar.topjava.service.impl;

import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

public class MealServiceImp implements MealService {

    private final MealsDao mealsDao;

    public MealServiceImp() {
        mealsDao = new MealsDao();
    }


    @Override
    public Meal getMeal(int id) {
        return mealsDao.getMeal(id);
    }

    @Override
    public List<Meal> getAllMeal() {
        return mealsDao.getAllMeal();
    }

    @Override
    public void createMeal(Meal meal) {
        mealsDao.createMeal(meal);
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        mealsDao.updateMeal(id, meal);
    }

    @Override
    public void deleteMeal(int id) {
        mealsDao.deleteMeal(id);
    }
}
