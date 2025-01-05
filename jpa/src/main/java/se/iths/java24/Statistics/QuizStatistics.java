package se.iths.java24.Statistics;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import se.iths.java24.Entity.Quiz;
import se.iths.java24.Entity.Result;
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

    // Calculate the average score for all quizzes or for a specific quiz.
    public static double getAverageScore(int quizId) {
        return JPAUtil.getEntityManager()
                .createQuery("SELECT AVG(r.score) FROM Result r WHERE r.quiz.id = :quizId", Double.class)
                .setParameter("quizId", quizId)
                .getSingleResult();
    }

    // The top 5 users with the highest scores for a specific quiz.
    public static List<Result> getTopScorers(int quizId, int limit) {
        return JPAUtil.getEntityManager()
                .createQuery("SELECT r FROM Result r WHERE r.quiz.id = :quizId ORDER BY r.score DESC", Result.class)
                .setParameter("quizId", quizId)
                .setMaxResults(limit)
                .getResultList();
    }

}

