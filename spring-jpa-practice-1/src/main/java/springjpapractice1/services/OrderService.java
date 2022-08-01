package springjpapractice1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.dto.OrderSearch;
import springjpapractice1.domain.entities.*;
import springjpapractice1.repositories.ItemRepository;
import springjpapractice1.repositories.MemberRepository;
import springjpapractice1.repositories.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = this.memberRepository.findOne(memberId);
        Item item = this.itemRepository.findOne(itemId);

        Delivery delivery = Delivery.builder()
                                    .address(member.getAddress())
                                    .build();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);
        this.orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = this.orderRepository.findOne(orderId);
        order.cancel();
    }

    @Transactional
    public List<Order> findOrders(OrderSearch orderSearch) {
        return this.orderRepository.findAll(orderSearch);
    }

}
