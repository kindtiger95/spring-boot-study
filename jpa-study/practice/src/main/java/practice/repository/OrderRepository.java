package practice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import practice.domain.Order;
import practice.domain.OrderSearch;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        //language=JPAQL
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                                    .setMaxResults(1000); //최대 1000건
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
    }

    public List<Order> findAll() {
        String query = "SELECT o FROM Order o " +
                "JOIN FETCH o.member m " +
                "JOIN FETCH o.delivery d";
        return this.em.createQuery(query, Order.class)
                      .getResultList();
    }

    public List<Order> findAllWithOrderItems() {
        String query = "SELECT DISTINCT o FROM Order o " +
                "JOIN FETCH o.member m " +
                "JOIN FETCH o.delivery d " +
                "JOIN FETCH o.orderItems oi " +
                "JOIN FETCH oi.item i";
        return this.em.createQuery(query, Order.class)
                      .getResultList();
    }

    public List<Order> findAllWithBatchSize(int offset, int limit) {
        String query = "SELECT o FROM Order o " +
                "JOIN FETCH o.member m " +
                "JOIN FETCH o.delivery d";
        return this.em.createQuery(query, Order.class)
                      .setFirstResult(offset)
                      .setMaxResults(limit)
                      .getResultList();
    }
}
