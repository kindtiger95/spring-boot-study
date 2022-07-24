package lec03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main (String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lec02");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member1 = new Member(1L, "Member1");
            em.persist(member1);
            em.flush();
            System.out.println("=============");

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("Change Name");
            em.detach(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
