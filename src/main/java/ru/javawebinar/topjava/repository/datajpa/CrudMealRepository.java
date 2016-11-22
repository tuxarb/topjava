package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer>{
    @Query("SELECT meal FROM Meal meal WHERE meal.user.id=:userId ORDER BY meal.dateTime DESC")
    List<Meal> getAll(@Param("userId") int userId);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Query("SELECT meal FROM Meal meal WHERE meal.id=:id AND meal.user.id=:userId")
    Meal get(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal meal WHERE meal.id=:id AND meal.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT meal FROM Meal meal WHERE meal.user.id=:userId AND meal.dateTime BETWEEN :startDate AND :endDate " +
            "ORDER BY meal.dateTime DESC")
    List<Meal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT meal FROM Meal meal WHERE meal.id=?1 AND meal.user.id=?2")
    Meal getWithUser(int id, int userId);
}
