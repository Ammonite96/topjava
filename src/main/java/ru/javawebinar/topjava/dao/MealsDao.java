package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDao {

    private final AtomicInteger MEAL_COUNT = new AtomicInteger(0);
    private List<Meal> mealList = new CopyOnWriteArrayList<>();

    {
        MealsUtil.meals.forEach(this::createMeal);
    }

    public void createMeal(Meal meal) {
        meal.setId(MEAL_COUNT.incrementAndGet());
        mealList.add(meal);
    }

    public List<Meal> getAllMeal() {
        return mealList;
    }

    public Meal getMeal(int id) {
        return mealList.stream().filter(meal -> meal.getId() == id).findAny().orElse(null);
    }

    public void updateMeal(int id, Meal meal) {
        Meal mealToUpdate = getMeal(id);
        mealToUpdate.setId(id);
        mealToUpdate.setDateTime(meal.getDateTime());
        mealToUpdate.setDescription(meal.getDescription());
        mealToUpdate.setCalories(meal.getCalories());
    }

    public void deleteMeal(Integer id) {
        mealList.removeIf(meal -> meal.getId().equals(id));
    }
}
