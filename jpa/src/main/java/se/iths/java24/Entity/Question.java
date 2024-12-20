package se.iths.java24.Entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty_level;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Answer> answers;

    public Question() {
    }

    public Question(Quiz quiz, String text, DifficultyLevel difficulty_level) {
        this.quiz = quiz;
        this.text = text;
        this.difficulty_level = difficulty_level;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DifficultyLevel getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(DifficultyLevel difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(question_id, question.question_id) && Objects.equals(quiz, question.quiz) && Objects.equals(text, question.text) && difficulty_level == question.difficulty_level && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question_id, quiz, text, difficulty_level, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", quiz=" + //quiz.getQuiz_id() +
                ", text='" + text + '\'' +
                ", difficulty_level=" + difficulty_level +
                '}';
    }
}

