package practice.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.dto.OrderItemQueryDto;
import practice.dto.OrderQueryDto;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;


    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();

        result.forEach(o -> {
            List<OrderItemQueryDto> orderItemList = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItemList);
        });
        return result;
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(toOrderIds(result));
        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
        return result;
    }


    private Object toOrderIds(List<OrderQueryDto> result) {
        return result.stream()
                     .map(OrderQueryDto::getOrderId)
                     .collect(Collectors.toList());
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(Object orderIds) {
        String query = "SELECT new practice.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
            + " FROM OrderItem AS oi"
            + " JOIN oi.item AS i"
            + " WHERE oi.order.id in :orderIds";
        List<OrderItemQueryDto> orderItemQueryDtos = em.createQuery(query, OrderItemQueryDto.class)
                                                       .setParameter("orderIds", orderIds)
                                                       .getResultList();

        return orderItemQueryDtos.stream().collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
    }

    private List<OrderQueryDto> findOrders() {
        String query = "SELECT new practice.dto.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) FROM Order AS o"
            + " JOIN o.member AS m"
            + " JOIN o.delivery AS d";
        return em.createQuery(query, OrderQueryDto.class)
                 .getResultList();
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        String query = "SELECT new practice.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
            + " FROM OrderItem AS oi"
            + " JOIN oi.item AS i"
            + " WHERE oi.order.id = :orderId";
        return em.createQuery(query, OrderItemQueryDto.class)
                 .setParameter("orderId", orderId)
                 .getResultList();
    }
}
