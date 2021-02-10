package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.impl.MealServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class UpdateMealServlet extends HttpServlet {

    private static final Logger log = getLogger(UpdateMealServlet.class);
    private MealService mealService = new MealServiceImp();
    private List<Meal> mealsList = mealService.getAllMeal();
    private Integer id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to update");
        id = Integer.valueOf(request.getParameter("id"));
        Meal meal = mealsList.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        request.setAttribute("meal", meal);

        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("redirect to update");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("date"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        mealService.updateMeal(id, new Meal(localDateTime, description, calories));
        response.sendRedirect("/topjava/meals");
    }
}
