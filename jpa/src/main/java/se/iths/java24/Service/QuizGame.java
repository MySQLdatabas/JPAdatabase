package se.iths.java24.Service;

import se.iths.java24.Entity.*;
import se.iths.java24.JPAUtil;
import se.iths.java24.Repository.*;

import java.util.List;
import java.util.Scanner;


public class QuizGame {

    public static void playQuiz(Scanner sc) {
        QuizRepository quizRepository = new QuizRepository();
        AnswerRepository answerRepository = new AnswerRepository();
        QuestionRepository questionRepository = new QuestionRepository();
        ResultRepository resultRepository = new ResultRepository();

        JPAUtil.inTransaction(entityManager -> {
            UserRepository userRepository = new UserRepository(entityManager);
            UserService userService = new UserService(userRepository);


            System.out.println("Create a new user");
            System.out.println("Enter username:");
            String username = sc.nextLine();
            System.out.println("Enter email");
            String email = sc.nextLine();
            System.out.println("Enter password");
            String password = sc.nextLine();

            User newUser = new User(username, email, password);
            userService.createUser(newUser);
            System.out.println("User created. Time to quiz.");

            System.out.println("Choose which quiz to play");
            List<Quiz> quizzes = quizRepository.getAllQuiz();
            for (Quiz quiz : quizzes) {
                System.out.println("ID: " + quiz.getQuizId() + " - " + quiz.getTitle());
            }

            System.out.println("Write ID for the quiz you want to play");
            int quizId = sc.nextInt();


            Quiz selectedQuiz = quizRepository.getQuizById(quizId);
            if (selectedQuiz == null) {
                System.out.println("Quiz doesn't exist.");
                return;
            }

            System.out.println("You're playing: " + selectedQuiz.getTitle());
            List<Question> questions = questionRepository.listQuestions(quizId);

            int score = 0;

            for (Question question : questions) {
                System.out.println("Question: " + question.getText());
                List<Answer> answers = answerRepository.listAnswers(question.getQuestion_id());

                int i = 1;
                for (Answer answer : answers) {
                    System.out.println(i + ". " + answer.getAnswerText());
                    i++;
                }

                System.out.println("Type the number of the answer:");
                int userChoice = sc.nextInt();

                if (answers.get(userChoice - 1).getIsCorrect()) {
                    score++;
                    System.out.println("Right answer!");
                } else {
                    System.out.println("That's not right. You were wrong.");
                }
            }

            Quiz quiz = resultRepository.getQuizById(quizId);
            User user = userRepository.getUserIdByUsername(username);
            if (user == null) {
                System.out.println("user doesn't exist.");
            }

            System.out.println(user.getUserId());
            System.out.println(quiz.getQuizId());
            Result newResult = new Result();
            newResult.setQuiz(quiz);
            newResult.setUser(user);
            newResult.setScore(score);

            entityManager.persist(newResult);

            System.out.println("You got " + score + " points.");
        });

    }
}
