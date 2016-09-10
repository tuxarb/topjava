package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet{
     private static final Logger LOG = getLogger(MealServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("[" + LocalDateTime.now() + "] Creating and filling list of meals...");
        Set<MealWithExceed> mealList = MealsUtil.initAndGetMealsSet();
        req.setAttribute("mealList", mealList);

        LOG.debug("[" + LocalDateTime.now() + "] Redirect to mealList");
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }
}
