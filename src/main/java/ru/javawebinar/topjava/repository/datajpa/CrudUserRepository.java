package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer>{
    @Override
    List<User> findAll(Sort sort);

    @Override
    @Transactional
    User save(User user);

    @Override
    User findOne(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM User user WHERE user.id=:id")
    int delete(@Param("id") int id);

    User getByEmail(String email);

    @Query("SELECT user FROM User user WHERE user.id = ?1")
    User getWithMeals(int id);
}
