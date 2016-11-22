package ru.javawebinar.topjava.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return entityManager.createQuery("DELETE FROM User user WHERE user.id=:id")
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return entityManager.createQuery("SELECT user FROM User user WHERE user.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT user FROM User user  ORDER BY user.name, user.email", User.class)
                .getResultList();
    }
}
