package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private ConfigurableApplicationContext context;
    private MealRestController mealController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new ClassPathXmlApplicationContext("spring/spring-app");
        mealController = context.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        if (context != null)
            context.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null) {
            String id = req.getParameter("id");
            LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"));
            String description = req.getParameter("description");
            int calories = Integer.valueOf(req.getParameter("calories"));

            final Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id), ldt, description, calories);
            if (id.isEmpty()) {
                LOG.info("Create meal={}", meal);
                mealController.create(meal);
            } else {
                LOG.info("Update meal={}", meal);
                mealController.update(meal, Integer.valueOf(id));
            }
            resp.sendRedirect("meals");
        }
        else
            if ("filter".equals(action))
            {
                LocalDate startDate = TimeUtil.parseLocalDate(req.getParameter("startDate"));
                LocalDate endDate = TimeUtil.parseLocalDate(req.getParameter("endDate"));
                LocalTime startTime = TimeUtil.parseLocalTime(req.getParameter("startTime"));
                LocalTime endTime = TimeUtil.parseLocalTime(req.getParameter("endTime"));
                req.setAttribute("mealList", mealController.getBetween(startTime, endTime, startDate, endDate));
                req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");

        if (action == null) {
            LOG.debug("Creating and filling list of meals...");
            List<MealWithExceed> mealList = mealController.getAll();
            req.setAttribute("mealList", mealList);
            req.getRequestDispatcher("mealList.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            int id = getId(req);
            LOG.info("Delete meal with id={}", id);
            mealController.delete(id);
            resp.sendRedirect("meals");
        } else if ("update".equals(action) || "create".equals(action)) {
            final Meal meal = "create".equals(action) ? new Meal(
                    LocalDateTime.now().withNano(0).withSecond(0), "", 0) : mealController.get(getId(req));
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealUpdate.jsp").forward(req, resp);
        }
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
