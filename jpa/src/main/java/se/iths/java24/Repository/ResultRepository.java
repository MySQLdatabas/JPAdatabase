package se.iths.java24.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import se.iths.java24.Entity.Quiz;
import se.iths.java24.Entity.Result;
import se.iths.java24.Entity.User;
import se.iths.java24.JPAUtil;

import java.util.List;

public class ResultRepository {


    public void createResult(Result result) {
        JPAUtil.inTransaction(em -> em.persist(result));
    }

    public Result getResultById(int resultId) {
    return JPAUtil.getEntityManager().find(Result.class, resultId);
    }

    public List<Result> getAllResults() {
        return JPAUtil.getEntityManager()
                .createQuery("select r from Result r", Result.class)
                .getResultList();
    }

    public User getUserById(int id) {
        return (User) JPAUtil.getEntityManager().find(User.class, id);
    }

    public Quiz getQuizById(int id) {
        return (Quiz) JPAUtil.getEntityManager().find(Quiz.class, id);
    }

    public void updateResult(Result resultToUpdate) {
        JPAUtil.inTransaction(em -> em.merge(resultToUpdate));
    }

    public void deleteResultById(int resultId) {
        JPAUtil.inTransaction(em -> em.remove(em.find(Result.class, resultId)));
    }
}
