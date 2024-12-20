package se.iths.java24.Repository;

import jakarta.persistence.EntityManager;
import se.iths.java24.Entity.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createUser(User user) {
        System.out.println("Persisting user: " + user);
        entityManager.persist(user);
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public Optional<User> getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
