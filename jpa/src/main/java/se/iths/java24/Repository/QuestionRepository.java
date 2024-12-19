package se.iths.java24.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.Entity.Question;
import se.iths.java24.JPAUtil;

import java.util.List;

public class QuestionRepository  {
    public List<Question> listQuestions(int quizId) {
        return JPAUtil.getEntityManager().createQuery("SELECT q.text, q.difficulty FROM Question q JOIN Quiz qu on q.quiz_id = :quizId ORDER BY difficulty", Question.class).getResultList();
    }
}