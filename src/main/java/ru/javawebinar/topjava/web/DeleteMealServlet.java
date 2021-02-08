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
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DeleteMealServlet extends HttpServlet {
    private static final Logger log = getLogger(DeleteMealServlet.class);
    private MealService mealService = new MealServiceImp();
    private List<Meal> mealsList = mealService.getAllMeal();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to delete");
        Meal meal = mealsList.stream().filter(m -> m.getId() == Integer.parseInt(req.getParameter("id"))).findAny().orElse(null);
        req.setAttribute("meal", meal);

        req.getRequestDispatcher("/delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to delete");
        req.setCharacterEncoding("UTF-8");
        mealService.deleteMeal(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/topjava/meals");
    }
}
