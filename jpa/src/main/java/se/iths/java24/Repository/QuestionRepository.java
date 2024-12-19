package se.iths.java24.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.Entity.Question;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuizQuiz_id(Long quizId);
}