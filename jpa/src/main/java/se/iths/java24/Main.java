package se.iths.java24;

import jakarta.persistence.EntityManager;


public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();


        // Start transaction
        em.getTransaction().begin();

        try {
            // Create new User and save it in database
            User newUser = new User();
            newUser.setUsername("coder5");
            newUser.setEmail("coder5@example.com");
            newUser.setPassword("hashed_password5");

            // Save User in database
            em.persist(newUser);

            // Commit transaktion
            em.getTransaction().commit();

            // Get and print User
            User user = em.find(User.class, newUser.getUserId());
            System.out.println("User from DB: " + user);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
