package lec10;

import lec10.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InsertMockingData {
    private EntityManagerFactory emf;

    public InsertMockingData(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertMocking() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            List<Member> members = new ArrayList<>();
            List<Orders> orders = new ArrayList<>();
            List<Delivery> deliveries = new ArrayList<>();

            String user = "user";
            String city = "city";
            String street = "street";
            String zip = "zip";
            for (int i = 0; i < 20; i++) {
                members.add(new Member(user + i, city + i, street + i, zip + i));
            }

            for (int i = 0; i < 20; i++) {
                deliveries.add(new Delivery(members.get(i).getCity(), members.get(i).getZipcode(), DeliveryStatus.START));
                orders.add(new Orders(LocalDateTime.now(), OrderStatus.START, members.get(i), deliveries.get(i)));
            }

            for (int i = 0; i < 20; i++) {
                em.persist(members.get(i));
                em.persist(orders.get(i));
                em.persist(deliveries.get(i));
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
