package hellojpa;

import hellojpa.jpashop.domain.Member;
import java.util.List;
import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("test1");
            member.setStreet("test1");
            member.setCity("test1");

            System.out.println("===============1=============");
            em.persist(member);
            System.out.println("===============2=============");
            member.setStreet("change street");
            em.remove(member);
            System.out.println("===============3=============");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
