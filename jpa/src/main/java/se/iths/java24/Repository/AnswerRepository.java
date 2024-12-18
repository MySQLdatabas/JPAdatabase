package se.iths.java24.Repository;

import jakarta.persistence.EntityManager;
import se.iths.java24.Entity.Answer;
import se.iths.java24.Entity.User;
import se.iths.java24.JPAUtil;

public class AnswerRepository {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();


        // Start transaction
        em.getTransaction().begin();

        try {
            // Create new User and save it in database
            Answer newAnswer = new Answer();
            newAnswer.setAnswerText("blabla");
            newAnswer.getIsCorrect();

            // Save User in database
            em.persist(newAnswer);

            // Commit transaktion
            em.getTransaction().commit();

//            Get and print User
//            User user = em.find(User.class, newUser.getUserId());
//            System.out.println("User from DB: " + user);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
