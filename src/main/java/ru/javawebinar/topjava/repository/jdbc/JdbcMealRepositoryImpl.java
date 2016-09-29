package ru.javawebinar.topjava.repository.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Primary
@Repository
public class JdbcMealRepositoryImpl implements MealRepository {
    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private static final RowMapper<Meal> MAPPER = new RowMapper<Meal>() {
        @Override
        public Meal mapRow(ResultSet rs, int i) throws SQLException {
            return new Meal(rs.getInt("id"), rs.getTimestamp("date_time").toLocalDateTime(), rs.getString("description"),
                    rs.getInt("calories"));
        }
    };
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("Meals")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM users WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return meals.isEmpty() ? null : DataAccessUtils.singleResult(meals);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("userId", userId)
                .addValue("dateTime", meal.getDateTime())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories());

        if (meal.isNew()) {
            Number mealId = jdbcInsert.executeAndReturnKey(map);
            meal.setId(mealId.intValue());
        } else if (namedJdbcTemplate.update("UPDATE meals SET date_time=:dateTime, " +
                "description=:description, calories=:calories WHERE id=:id AND user_id=:userId", map) == 0)
            return null;

        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId) == 0;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM MEALS WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public Collection<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE date_time BETWEEN ? AND ?", ROW_MAPPER, startDate, endDate);
    }
}







