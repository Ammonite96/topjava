package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.impl.MealServiceImp;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);
    private MealService mealService = new MealServiceImp();
    private List<Meal> mealsList = mealService.getAllMeal();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<MealTo> mealWithExcesses = MealsUtil.filteredByCycles(mealsList, LocalTime.MIN,LocalTime.MAX,2000);

        req.setAttribute("mealTo", mealWithExcesses);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);

        //resp.sendRedirect("users.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Meal meal = new Meal(localDateTime, description, calories);
        mealService.createMeal(meal);
        doGet(req, resp);
    }

}
