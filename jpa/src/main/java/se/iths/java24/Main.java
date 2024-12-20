package se.iths.java24;

import jakarta.persistence.EntityManager;
import se.iths.java24.Entity.Answer;
import se.iths.java24.Entity.Question;
import se.iths.java24.Entity.User;
import se.iths.java24.Repository.AnswerRepository;

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
                    case 0 -> {System.out.println("\nReturning to main menu...");
                        return;}
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
                        Answer answerToUpdate = AnswerRepository.getAnswerById(answerId);

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

                        //QuestionId is the same as before

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
