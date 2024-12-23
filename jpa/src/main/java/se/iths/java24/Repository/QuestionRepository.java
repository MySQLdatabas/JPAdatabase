package se.iths.java24.Repository;


import se.iths.java24.Entity.Question;
import se.iths.java24.Entity.Quiz;
import se.iths.java24.JPAUtil;

import java.util.List;

public class QuestionRepository {


    public void createQuestion(Question question) {
        JPAUtil.inTransaction(em -> em.persist(question));
    }

    public void deleteQuestion(int id) {
        JPAUtil.inTransaction(em -> em.remove(id));
    }

    public void updateQuestion(Question question) {
        JPAUtil.inTransaction(em -> em.merge(question));
    }

    public Quiz getQuizById(int id) {
        return JPAUtil.getEntityManager().find(Quiz.class, id);
    }

    public List<Question> listQuestions(int quizId) {
        return JPAUtil.getEntityManager()
                .createQuery("SELECT q FROM Question q JOIN q.quiz qu WHERE qu.id = :quizId ORDER BY q.difficulty_level", Question.class)
                .setParameter("quizId", quizId)
                .getResultList();
    }

    public Question getQuestionById(int id) {
        return JPAUtil.getEntityManager().find(Question.class, id);
    }
}