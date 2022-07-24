package lec02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lec02");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member1 = new Member();
            Member member2 = new Member();
            Member member3 = new Member();

            member1.setName("Member1");
            member2.setName("Member2");
            member3.setName("Member3");

            member1.setId(1L);
            member2.setId(2L);
            member3.setId(3L);

            /*
            * id의 generate strategy가 identity이면 persist 시점에 insert 쿼리 날라간다.
            * auto increment이므로 db에 한번 저장해야 id 값을 알 수 있기 때문이다.
            **/

            System.out.println("==== BEFORE ====");
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            System.out.println("==== AFTER ====");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
