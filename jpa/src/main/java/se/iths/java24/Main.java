package se.iths.java24;

import jakarta.persistence.EntityManager; // For interacting with the database
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.iths.java24.Entity.DifficultyLevel;
import se.iths.java24.Entity.Question;
import se.iths.java24.Entity.User;  // Import the User entity class
import se.iths.java24.Repository.QuestionRepository; // Import the QuestionRepository interface

import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Scanner  sc = new Scanner(System.in);


    public static void main(String[] args) {
        Boolean quit = false;
        printAction();

        while (!quit) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0 -> {System.out.println("\nStänger ner...");
                    sc.close();
                quit = true;}
                case 1 -> manageQuiz(sc);
                case 2 -> manageAnswers(sc);
                case 3 -> manageQuestions(sc);
                case 4 -> manageResult(sc);
                case 5 -> manageUsers(sc);
                default -> System.out.println("\nOgiltigt val, försök igen");
            }

        }
    }


    private static void manageQuiz(Scanner sc) {
        System.out.println("\nManage quiz:");
        System.out.println(""" 
                1 - View quiz
                2 - Create quiz
                3 - Update quiz
                4 - Delete quiz
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
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                }
                default -> System.out.println("\nOgiltigt val, försök igen");
            }
        }
    }

    private static void manageResult(Scanner sc) {
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
                    case 0 -> {System.out.println("\nReturning to main menu...");
                        return;}
                    case 1 -> {}
                    case 2 -> {}
                    case 3 -> {}
                    case 4 -> {}
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
                    case 0 -> {System.out.println("\nReturning to main menu...");
                        return;}
                    case 1 -> {
                        System.out.println("Enter id for quiz: ");
                        int id  = sc.nextInt();
                        List<Question> questions = questionRepository.listQuestions(id);
                        questions.forEach(question ->
                                System.out.println("ID: " + question.getQuestion_id() +
                                        ", Text: " + question.getText() +
                                        ", Difficulty: " + question.getDifficulty_level()));
                    }
                    case 2 -> {
                        System.out.println("Enter question title:");
                        String text = sc.nextLine();

                        System.out.println("Enter question description:");
                        String description = sc.nextLine();

                        System.out.println("Enter difficulty level:");
                        System.out.println("1. Easy");
                        System.out.println("2. Medium");
                        System.out.println("3. Hard");
                        System.out.print("Enter your choice (1-3): ");
                        int difficultyChoice = sc.nextInt();
                        sc.nextLine(); // Consume the newline character

                        Question newQuestion = new Question();
                        newQuestion.setText(text);
                        newQuestion.setDifficulty_level(getDifficultyLevelByChoice(difficultyChoice));

                        // Assuming questionRepository is an instance of a class
                        // that handles database interactions
                        questionRepository.createQuestion(newQuestion);

                        System.out.println("Question created successfully.");
                    }

                    case 3 -> {
                        System.out.println("Enter the ID of the question to update:");
                        int id = sc.nextInt();
                        sc.nextLine(); // Consume the newline character

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
                        sc.nextLine(); // Consume the newline character

                        if (difficultyChoice != 4) {
                            questionToUpdate.setDifficulty_level(getDifficultyLevelByChoice(difficultyChoice));
                        }

                        questionRepository.updateQuestion(questionToUpdate);
                        System.out.println("Question updated successfully.");
                    }
                    case 4 -> {
                        System.out.println("Enter the ID of the question to delete:");
                        int id = sc.nextInt();
                        sc.nextLine();

                        questionRepository.deleteQuestion(id);
                        System.out.println("Question deleted successfully.");
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
                    case 0 -> {System.out.println("\nReturning to main menu...");
                        return;}
                    case 1 -> {}
                    case 2 -> {}
                    case 3 -> {}
                    case 4 -> {}
                    default -> System.out.println("\nOgiltigt val, försök igen");
                }
            }
    }

    private static void manageUsers(Scanner sc) {
        System.out.println("\nManage users:");
        System.out.println(""" 
                    1 - View users
                    2 - Create users
                    3 - Update users
                    4 - Delete users
                    0 - Back to main menu
                    """);

            while (true) {
                int action = sc.nextInt();
                sc.nextLine();

                switch (action) {
                    case 0 -> {System.out.println("\nReturning to main menu...");
                        return;}
                    case 1 -> {}
                    case 2 -> {}
                    case 3 -> {}
                    case 4 -> {}
                    default -> System.out.println("\nOgiltigt val, försök igen");
                }
            }

    }


    private static void printAction() {
            System.out.println("\nQuiz menu: \n");
            System.out.println(""" 
                    1 - Manage quiz
                    2 - Manage answers
                    3 - Manage questions
                    4 - Manage users
                    0 - Exit
                    """);
    }
}
