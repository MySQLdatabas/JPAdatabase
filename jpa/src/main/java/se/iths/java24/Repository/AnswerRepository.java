package se.iths.java24.Repository;

import se.iths.java24.Entity.Answer;
import se.iths.java24.Entity.Question;
import se.iths.java24.JPAUtil;

import java.util.List;

public class AnswerRepository {

    public void createAnswer(Answer answer) {
        JPAUtil.inTransaction(em -> em.persist(answer));
    }

    public static Answer getAnswerById(int id) {
        return JPAUtil.getEntityManager().find(Answer.class, id);
    }

    public Question getQuestionById(int questionId) {
        return JPAUtil.getEntityManager().find(Question.class, questionId);
    }

    public List<Answer> getAllAnswer() {
        return JPAUtil.getEntityManager()
                .createQuery("select question, answerText from Answer", Answer.class)
                .getResultList();
    }

    public void updateAnswer(Answer answer) {
        JPAUtil.inTransaction(em -> em.merge(answer));
    }

    public void deleteAnswer(int id) {
        JPAUtil.inTransaction(em -> em.remove(em.find(Answer.class, id)));
    }

    public List<Answer> listAnswers(int questionId) {
        return JPAUtil.getEntityManager()
                .createQuery("SELECT a FROM Answer a JOIN a.question q WHERE q.id = :questionId", Answer.class)
                .setParameter("questionId", questionId)
                .getResultList();
    }

}
