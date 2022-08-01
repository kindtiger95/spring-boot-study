package springjpapractice1.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springjpapractice1.domain.dto.OrderSearch;
import springjpapractice1.domain.entities.Order;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Order> findAll(OrderSearch orderSearch) {
        String query = "SELECT o FROM Order o";
        return this.em.createQuery(query, Order.class)
                      .getResultList();
    }
}
