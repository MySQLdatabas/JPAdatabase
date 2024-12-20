package se.iths.java24.Repository;

import se.iths.java24.Entity.Quiz;
import se.iths.java24.JPAUtil;

import java.util.List;

public class QuizRepository {
    public void createQuiz(Quiz quiz) {
        JPAUtil.inTransaction(em -> em.persist(quiz));
    }

    public Quiz getQuizById(int id) {
        return JPAUtil.getEntityManager().find(Quiz.class, id);
    }

    public List<Quiz> getAllQuiz() {
        return JPAUtil.getEntityManager()
                .createQuery("select q from Quiz q", Quiz.class)
                .getResultList();
    }

    public void updateQuiz(Quiz quiz) {
        JPAUtil.inTransaction(em -> em.merge(quiz));
    }

    public void deleteQuiz(int id) {
        JPAUtil.inTransaction(em -> em.remove(em.find(Quiz.class, id)));
    }
}
