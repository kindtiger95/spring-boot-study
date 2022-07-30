package springjpapractice1.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springjpapractice1.domain.entities.Order;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        this.em.persist(order);
    }

    public Order findOne(Long id) {
        return this.em.find(Order.class, id);
    }
}
