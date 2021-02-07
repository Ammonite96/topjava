package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealsDao {

    private static int MEAL_COUNT;
    private List<Meal> mealList;

    {
        mealList = new ArrayList<>();
               mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.FEBRUARY, 1, 0, 0), "Оо в Феврале", 100));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.FEBRUARY, 1, 8, 15), "Оо", 200));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0), "Завтрак", 1500));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.APRIL, 25, 7, 15), "Оо в Апреле", 600));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.APRIL, 25, 11, 15), "Завтрак", 200));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.APRIL, 25, 12, 15), "Обед", 800));
        mealList.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.APRIL, 25, 21, 15), "Ужин", 500));

    }

    public void createMeal(Meal meal) {
        meal.setId(++MEAL_COUNT);
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

    public void deleteMeal(int id) {
        mealList.removeIf(meal -> meal.getId() == id);
    }
}
