package se.iths.java24.Service;

import se.iths.java24.Entity.Answer;
import se.iths.java24.Entity.Question;
import se.iths.java24.Entity.Quiz;
import se.iths.java24.Repository.AnswerRepository;
import se.iths.java24.Repository.QuizRepository;
import se.iths.java24.Repository.QuestionRepository;

import java.util.List;
import java.util.Scanner;


public class QuizGame {

    public static void playQuiz(Scanner sc) {
        QuizRepository quizRepository = new QuizRepository();
        AnswerRepository answerRepository = new AnswerRepository();
        QuestionRepository questionRepository = new QuestionRepository();

        System.out.println("Välj ett quiz att spela:");
        List<Quiz> quizzes = quizRepository.getAllQuiz();
        for (Quiz quiz : quizzes) {
            System.out.println("ID: " + quiz.getQuizId() + " - " + quiz.getTitle());
        }

        System.out.println("Skriv ID för quizet du vill spela:");
        int quizId = sc.nextInt();
        sc.nextLine();

        Quiz selectedQuiz = quizRepository.getQuizById(quizId);
        if (selectedQuiz == null) {
            System.out.println("Quizet finns inte.");
            return;
        }

        System.out.println("Du spelar quizet: " + selectedQuiz.getTitle());
        List<Question> questions = questionRepository.listQuestions(quizId);
        int score = 0;

        for (Question question : questions) {
            System.out.println("Fråga: " + question.getText());
            List<Answer> answers = answerRepository.listAnswers(question.getQuestion_id());

            int i = 1;
            for (Answer answer : answers) {
                System.out.println(i + ". " + answer.getAnswerText());
                i++;
            }

            System.out.println("Välj svar genom att ange numret på svaret:");
            int userChoice = sc.nextInt();
            sc.nextLine();

            if (answers.get(userChoice - 1).getIsCorrect()) {
                score++;
                System.out.println("Rätt svar!");
            } else {
                System.out.println("Fel svar.");
            }
        }

        System.out.println("Quizet är klart! Du fick " + score + " poäng.");
    }


}
