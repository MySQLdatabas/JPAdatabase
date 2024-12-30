package se.iths.java24;


import jakarta.persistence.EntityManager; // For interacting with the database
import se.iths.java24.Entity.*;
import se.iths.java24.Repository.*;
import jakarta.persistence.EntityManager;
import se.iths.java24.Service.UserService;
import se.iths.java24.Statistics.QuizStatistics;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        testConnection();
        printActions();
        boolean quit = false;


        while (!quit) {
            System.out.println("\nEnter your choice:");
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0 -> {
                    System.out.println("\nExiting...");
                    sc.close();
                    quit = true;
                }
                case 1 -> manageQuiz(sc);
                case 2 -> manageAnswers(sc);
                case 3 -> manageQuestions(sc);
                case 4 -> manageResult(sc);
                case 5 -> manageUsers();
                default -> System.out.println("\nInvalid choice, try again.");
            }

            if (!quit) {
                printActions();
            }
        }
    }


    private static void manageQuiz(Scanner sc) {
        QuizRepository quizRepository = new QuizRepository();
        System.out.println("\nManage quiz:");
        System.out.println(""" 
                1 - View quiz
                2 - Create quiz
                3 - Update quiz
                4 - Delete quiz
                5 - View quiz statistics
                0 - Back to main menu
                """);

        while (true) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0 -> {
                    System.out.println("\nReturning to main menu...");
                    return;
                }
                case 1 -> {
                    List<Quiz> quizzes = quizRepository.getAllQuiz();
                    quizzes.forEach(quiz ->
                            System.out.println("ID: " + quiz.getQuizId() +
                                    ", Title: " + quiz.getTitle() +
                                    ", Description: " + quiz.getDescription()));
                }
                case 2 -> {
                    System.out.println("Enter quiz title:");
                    String title = sc.nextLine();
                    System.out.println("Enter quiz description:");
                    String description = sc.nextLine();

                    Quiz newQuiz = new Quiz();
                    newQuiz.setTitle(title);
                    newQuiz.setDescription(description);

                    quizRepository.createQuiz(newQuiz);
                    System.out.println("Quiz created successfully.");
                }
                case 3 -> {
                    System.out.println("Enter the ID of the quiz to update:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Quiz quizToUpdate = quizRepository.getQuizById(id);
                    if (quizToUpdate == null) {
                        System.out.println("Quiz not found.");
                        break;
                    }

                    System.out.println("Enter new title (leave blank to keep current):");
                    String newTitle = sc.nextLine();
                    if (!newTitle.isBlank()) {
                        quizToUpdate.setTitle(newTitle);
                    }

                    System.out.println("Enter new description (leave blank to keep current):");
                    String newDescription = sc.nextLine();
                    if (!newDescription.isBlank()) {
                        quizToUpdate.setDescription(newDescription);
                    }

                    quizRepository.updateQuiz(quizToUpdate);
                    System.out.println("Quiz updated successfully.");
                }
                case 4 -> {
                    System.out.println("Enter the ID of the quiz to delete:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    quizRepository.deleteQuiz(id);
                    System.out.println("Quiz deleted successfully.");
                }
                case 5 -> {
                    System.out.println("1 - Total quizzes");
                    System.out.println("2 - Recent quizzes");
                    System.out.println("3 - Count quizzes by description length");
                    System.out.println("0 - Back");
                    int statAction = sc.nextInt();
                    sc.nextLine();

                    switch (statAction) {
                        case 0 -> System.out.println("Returning...");
                        case 1 -> System.out.println("Total quizzes: " + QuizStatistics.getTotalQuizzes());
                        case 2 -> {
                            System.out.println("Enter number of recent quizzes to fetch:");
                            int limit = sc.nextInt();
                            List<Quiz> recentQuizzes = QuizStatistics.getRecentQuizzes(limit);
                            recentQuizzes.forEach(System.out::println);
                        }
                        case 3 -> {
                            System.out.println("Enter minimum description length:");
                            int minLength = sc.nextInt();
                            System.out.println("Count: " + QuizStatistics.countQuizzesByDescriptionLength(minLength));
                        }
                        default -> System.out.println("Invalid choice.");
                    }
                }
                default -> System.out.println("\nOgiltigt val, försök igen");
            }
        }
    }

    private static void manageResult(Scanner sc) {
        ResultRepository resultRepository = new ResultRepository();
        System.out.println("\nManage result:");
        System.out.println(""" 
                1 - View result
                2 - Create result
                3 - Update result
                4 - Delete result
                0 - Back to main menu
                """);

        while (true) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0 -> {
                    System.out.println("\nReturning to main menu...");
                    return;
                }
                case 1 -> {
                    List<Result> results = resultRepository.getAllResults();
                    results.forEach(result ->
                            System.out.println("ID: " + result.getId() +
                                    ", User id: " + result.getUser() +
                                    ", Quiz id: " + result.getQuiz() +
                                    ", Score: " + result.getScore()));


                }
                case 2 -> {
                    System.out.println("Enter user id:");
                    int userId = sc.nextInt();
                    User user = resultRepository.getUserById(userId);
                    System.out.println("Enter quiz id:");
                    int quizId = sc.nextInt();
                    Quiz quiz = resultRepository.getQuizById(quizId);
                    System.out.println("Enter score");
                    int score = sc.nextInt();

                    if (user != null || quiz != null) {
                        Result newResult = new Result();
                        newResult.setScore(score);
                        newResult.setUser(user);
                        newResult.setQuiz(quiz);

                        resultRepository.createResult(newResult);
                        System.out.println("Result created successfully.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter result ID to update");
                    int resultId = sc.nextInt();
                    Result resultToUpdate = resultRepository.getResultById(resultId);
                    if (resultToUpdate == null) {
                        System.out.println("Result not found.");
                        break;
                    }
                    System.out.println("Enter new score:");
                    int newScore = sc.nextInt();
                    resultToUpdate.setScore(newScore);

                    resultRepository.updateResult(resultToUpdate);
                    System.out.println("\nNew result created successfully");
                }
                case 4 -> {
                    System.out.println("Enter result id to delete:");
                    int resultId = sc.nextInt();
                    resultRepository.deleteResultById(resultId);
                    System.out.println("Result deleted successfully");
                }
                default -> System.out.println("\nOgiltigt val, försök igen");
            }
        }
    }

    private static void manageQuestions(Scanner sc) {

        QuestionRepository questionRepository = new QuestionRepository();
        System.out.println("\nManage questions:");
        System.out.println(""" 
                1 - View questions
                2 - Create question
                3 - Update question
                4 - Delete question
                0 - Back to main menu
                """);

        while (true) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0 -> {
                    System.out.println("\nReturning to main menu...");
                    return;
                }
                case 1 -> {
                    System.out.println("Enter id for quiz: ");
                    int id = sc.nextInt();
                    List<Question> questions = questionRepository.listQuestions(id);
                    questions.forEach(question ->
                            System.out.println("ID: " + question.getQuestion_id() +
                                    ", Text: " + question.getText() +
                                    ", Difficulty: " + question.getDifficulty_level()));
                }
                case 2 -> {
                    System.out.println("Enter question description:");
                    String description = sc.nextLine();

                    System.out.println("Enter difficulty level:");
                    System.out.println("1. Easy");
                    System.out.println("2. Medium");
                    System.out.println("3. Hard");
                    System.out.print("Enter your choice (1-3): ");
                    int difficultyChoice = sc.nextInt();

                    System.out.println("Enter quiz id:");
                    int quizId = sc.nextInt();
                    Quiz quiz = questionRepository.getQuizById(quizId);

                    Question newQuestion = new Question();
                    newQuestion.setQuiz(quiz);
                    newQuestion.setText(description);
                    newQuestion.setDifficulty_level(getDifficultyLevelByChoice(difficultyChoice));

                    questionRepository.createQuestion(newQuestion);

                    System.out.println("Question created successfully.");
                }

                case 3 -> {
                    System.out.println("Enter the ID of the question to update:");
                    int id = sc.nextInt();

                    Question questionToUpdate = questionRepository.getQuestionById(id);
                    if (questionToUpdate == null) {
                        System.out.println("Question not found.");
                        break;
                    }

                    System.out.println("Enter new title (leave blank to keep current):");
                    String newTitle = sc.nextLine();
                    if (!newTitle.isBlank()) {
                        questionToUpdate.setText(newTitle);
                    }

                    System.out.println("Enter new difficulty level:");
                    System.out.println("1. Easy");
                    System.out.println("2. Medium");
                    System.out.println("3. Hard");
                    System.out.println("4. Keep Current");
                    System.out.print("Enter your choice (1-4): ");
                    int difficultyChoice = sc.nextInt();

                    if (difficultyChoice != 4) {
                        questionToUpdate.setDifficulty_level(getDifficultyLevelByChoice(difficultyChoice));
                    }

                    questionRepository.updateQuestion(questionToUpdate);
                    System.out.println("Question updated successfully.");
                }
                case 4 -> {
                    System.out.println("Enter the ID of the question to delete:");
                    int id = sc.nextInt();
                    Question questionToDelete = questionRepository.deleteQuestion(id);

                }
                default -> System.out.println("\nOgiltigt val, försök igen");
            }
        }
    }

    private static DifficultyLevel getDifficultyLevelByChoice(int choice) {
        switch (choice) {
            case 1:
                return DifficultyLevel.Easy;
            case 2:
                return DifficultyLevel.Medium;
            case 3:
                return DifficultyLevel.Hard;
            default:
                System.out.println("Invalid choice. Defaulting to Easy.");
                return DifficultyLevel.Easy;
        }
    }

    private static void manageAnswers(Scanner sc) {
        AnswerRepository answerRepository = new AnswerRepository();
        System.out.println("\nManage answers:");
        System.out.println(""" 
                1 - View answer
                2 - Create answer
                3 - Update answer
                4 - Delete answer
                0 - Back to main menu
                """);

        while (true) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0 -> {
                    System.out.println("\nReturning to main menu...");
                    return;
                }
                case 1 -> {
                    System.out.println("\nEnter id for the question you want answers for");
                    int questionId = sc.nextInt();
                    List<Answer> answers = answerRepository.listAnswers(questionId);
                    answers.forEach(answer ->
                            System.out.println("ID: " + answer.getId() +
                                    ", Answer:" + answer.getAnswerText() +
                                    ", Correct: " + answer.getIsCorrect())
                    );
                }
                case 2 -> {
                    System.out.println("\nEnter the answer:");
                    String answerText = sc.nextLine();
                    System.out.println("\nIs the answer true or false?");
                    Boolean isCorrectAnswer = Boolean.valueOf(sc.nextLine());
                    System.out.println("\nEnter Question ID for your answer:");
                    int questionId = sc.nextInt();
                    Question question = answerRepository.getQuestionById(questionId);

                    if (question != null) {
                        Answer newAnswer = new Answer();
                        newAnswer.setIsCorrect(isCorrectAnswer);
                        newAnswer.setAnswerText(answerText);
                        newAnswer.setQuestion(question);

                        answerRepository.createAnswer(newAnswer);
                        System.out.println("\nNew answer created successfully");
                    } else {
                        System.out.println("Question not found with ID: " + questionId);
                    }
                }
                case 3 -> {
                    System.out.println("\nEnter answer id:");
                    int answerId = sc.nextInt();
                    sc.nextLine();
                    Answer answerToUpdate = answerRepository.getAnswerById(answerId);

                    if (answerToUpdate == null) {
                        System.out.println("Answer not found.");
                        break;
                    }

                    System.out.println("Enter new answer (leave blank to keep current):");
                    String newAnswerText = sc.nextLine();

                    if (!newAnswerText.isBlank()) {
                        answerToUpdate.setAnswerText(newAnswerText);
                    }

                    System.out.println("Enter if answer is true or false:");
                    Boolean newIsCorrect = Boolean.valueOf(sc.nextLine());
                    answerToUpdate.setIsCorrect(newIsCorrect);

                    answerRepository.updateAnswer(answerToUpdate);
                    System.out.println("\nNew answer created successfully");

                }
                case 4 -> {
                    System.out.println("\nEnter answer id to delete:");
                    int answerId = sc.nextInt();
                    sc.nextLine();
                    answerRepository.deleteAnswer(answerId);
                    System.out.println("\nAnswer deleted successfully");
                }
                default -> System.out.println("\nOgiltigt val, försök igen");
            }
        }
    }

    private static void manageUsers() {
        JPAUtil.inTransaction(entityManager -> {
            UserRepository userRepository = new UserRepository(entityManager);
            UserService userService = new UserService(userRepository);

            while (true) {
                System.out.println("\nManage users:");
                System.out.println("""
                        1 - View users
                        2 - Create users
                        3 - Update users
                        4 - Delete users
                        0 - Back to main menu
                        """);

                System.out.println("Enter your choice:");
                int action = sc.nextInt();
                sc.nextLine();

                switch (action) {
                    case 0 -> {
                        System.out.println("\nReturning to main menu...");
                        return;
                    }
                    case 1 -> {
                        List<User> users = userService.getAllUsers();
                        if (users.isEmpty()) {
                            System.out.println("No users found.");
                        } else {
                            users.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter user name:");
                        String name = sc.nextLine();
                        System.out.println("Enter user email:");
                        String email = sc.nextLine();
                        System.out.println("Enter user password:");
                        String password = sc.nextLine();

                        User newUser = new User(name, email, password);
                        userService.createUser(newUser);
                        System.out.println("User created successfully!");
                    }
                    case 3 -> {
                        System.out.println("Enter user ID to update:");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        Optional<User> userOptional = userService.getUserById(id);
                        if (userOptional.isPresent()) {
                            User user = userOptional.get();
                            System.out.println("Enter new name:");
                            user.setName(sc.nextLine());
                            System.out.println("Enter new email:");
                            user.setEmail(sc.nextLine());

                            userService.updateUser(user);
                            System.out.println("User updated successfully!");
                        } else {
                            System.out.println("User not found.");
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter user ID to delete:");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        userService.deleteUser(id);
                        System.out.println("User deleted successfully!");
                    }
                    default -> System.out.println("\nInvalid choice, try again.");
                }
            }
        });
    }

    private static void printActions() {
        System.out.println("\nQuiz menu: \n");
        System.out.println("""
                1 - Manage quiz
                2 - Manage answers
                3 - Manage questions
                4 - Manage results
                5 - Manage Users
                0 - Exit
                """);
    }

    public static void testConnection() {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            em.createNativeQuery("SELECT 1").getSingleResult();
            System.out.println("Connection is working!");
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}

