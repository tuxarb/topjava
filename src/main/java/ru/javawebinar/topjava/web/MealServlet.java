package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private MealRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryMealRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.valueOf(req.getParameter("calories"));

        Meal meal = new Meal(id.isEmpty() ? null : getId(req), ldt, description, calories);
        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        repository.save(meal);

        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");

        if (action == null) {
            LOG.debug("Creating and filling list of meals...");
            List<MealWithExceed> mealList = MealsUtil.getListWithExceed(
                    repository.getAll(),
                    MealsUtil.DEFAULT_CALORIES_PER_DAY
            );
            req.setAttribute("mealList", mealList);
            req.getRequestDispatcher("mealList.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            int id = getId(req);
            LOG.info("Delete {}", id);
            repository.delete(id);
            resp.sendRedirect("meals");
        } else if ("update".equals(action) || "create".equals(action)) {
            final Meal meal = "create".equals(action) ? new Meal(1, LocalDateTime.now(), "", 0) :
                    repository.get(getId(req));
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealUpdate.jsp").forward(req, resp);
        }
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
