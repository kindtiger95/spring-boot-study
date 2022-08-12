package lec09;

import lec09.entities.Address;
import lec09.entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lec09");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setAddress(new Address("uiwang", "pangyo-ro", "133122"));
            member.setName("test");
            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("================================");
            Member findMember = em.find(Member.class, 1L);
            System.out.println("================================");

            Address address = findMember.getAddress();

            // address 뭔가를 변경하려면
            findMember.setAddress(new Address("newwww city", address.getStreet(), address.getStreet()));

            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
