package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.Collection;

import static ru.javawebinar.topjava.web.SecurityUtil.*;

@Controller
public class MealRestController {

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal save(Meal meal){
        return service.save(meal, getAuthUserId());
    }

    public void delete(int id) {
        service.delete(id, getAuthUserId());
    }

    public Meal get(int id) {
        return service.get(id, getAuthUserId());
    }

    public Collection<MealTo> getAll() {
        return MealsUtil.getTos(service.getAll(getAuthUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Collection<MealTo> filteredByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return MealsUtil.getFilteredTos(service.getAllFilteredByDate(getAuthUserId(), startTime.toLocalDate(), endTime.toLocalDate()), MealsUtil.DEFAULT_CALORIES_PER_DAY, startTime.toLocalTime(), endTime.toLocalTime());
    }
}