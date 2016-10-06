package ru.javawebinar.topjava.repository.jpa;


import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = entityManager.createQuery(
                "SELECT meal FROM Meal meal WHERE meal.id=:id AND meal.user.id=:userId", Meal.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Transactional
    @Override
    public Meal save(Meal meal, int userId) {
        User user = entityManager.getReference(User.class, userId);
        meal.setUser(user);

        if (meal.isNew()) {
            entityManager.persist(meal);
            return meal;
        } else {
            if (get(meal.getId(), userId) == null)
                return null;
            return entityManager.merge(meal);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return entityManager.createQuery("DELETE FROM Meal meal WHERE meal.id=:id AND meal.user.id=:userId")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return entityManager.createQuery(
                "SELECT meal FROM Meal meal WHERE meal.user.id=:userId ORDER BY meal.dateTime DESC", Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Collection<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return entityManager.createQuery("SELECT meal FROM Meal meal WHERE meal.user.id=:userId AND " +
                "meal.dateTime BETWEEN :startDate AND :endDate ORDER BY meal.dateTime DESC", Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
