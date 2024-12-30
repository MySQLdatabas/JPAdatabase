package se.iths.java24.Statistics;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import se.iths.java24.Entity.Quiz;
import se.iths.java24.JPAUtil;

import java.util.List;

public class QuizStatistics {

    // Get the total number of quizzes
    public static long getTotalQuizzes() {
        return JPAUtil.getEntityManager().createQuery(
                "SELECT COUNT(q) FROM Quiz q", Long.class
        ).getSingleResult();
    }

    // Fetch a list of recently created quizzes
    public static List<Quiz> getRecentQuizzes(int limit) {
        return JPAUtil.getEntityManager().createQuery(
                "SELECT q FROM Quiz q ORDER BY q.createdAt DESC", Quiz.class
        ).setMaxResults(limit).getResultList();
    }

    // Count quizzes based on a minimum description length
    public static long countQuizzesByDescriptionLength(int minLength) {
        return JPAUtil.getEntityManager().createQuery(
                "SELECT COUNT(q) FROM Quiz q WHERE LENGTH(q.description) >= :minLength", Long.class
        ).setParameter("minLength", minLength).getSingleResult();
    }
}