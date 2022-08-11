package lec07;

import lec07.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lec07");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Book book = new Book();
            book.setAuthor("inbeom");
            book.setPrice(1000);
            em.persist(book);
            tx.commit();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
