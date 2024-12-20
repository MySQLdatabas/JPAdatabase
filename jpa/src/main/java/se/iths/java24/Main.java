package se.iths.java24;

import jakarta.persistence.EntityManager;
import se.iths.java24.Entity.Quiz;
import se.iths.java24.Entity.User;
import se.iths.java24.Repository.QuizRepository;
import se.iths.java24.Repository.UserRepository;
import se.iths.java24.Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        testConnection();

        boolean quit = false;
        printActions();

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
                    case 1 -> {}
                    case 2 -> {}
                    case 3 -> {}
                    case 4 -> {}
                    default -> System.out.println("\nOgiltigt val, försök igen");
                }
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

