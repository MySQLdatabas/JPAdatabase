package se.iths.java24;

import jakarta.persistence.EntityManager;
import se.iths.java24.Entity.User;

import java.util.Scanner;


public class Main {
    private static final Scanner  sc = new Scanner(System.in);


    public static void main(String[] args) {
        Boolean quit = false;

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
