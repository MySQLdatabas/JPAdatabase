package se.iths.java24.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.Entity.Question;
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

    public List<Question> listQuestions(int quizId) {
        return JPAUtil.getEntityManager()
                .createQuery("SELECT q.text, q.difficulty FROM Question q JOIN Quiz qu on q.quiz_id = qu.quiz_id WHERE qu.quiz_id = :quizId ORDER BY difficulty", Question.class)
                .getResultList();
    }

    public Question getQuestionById(int id) {
        return JPAUtil.getEntityManager().find(Question.class, id);
    }
}