package springjpapractice1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.Delivery;
import springjpapractice1.domain.DeliveryStatus;
import springjpapractice1.domain.Item;
import springjpapractice1.domain.Member;
import springjpapractice1.domain.Order;
import springjpapractice1.domain.OrderItem;
import springjpapractice1.repository.ItemRepository;
import springjpapractice1.repository.MemberRepository;
import springjpapractice1.repository.OrderRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public Long order(Long memberId, Long itemId, int count) {
        Member member = this.memberRepository.findById(memberId);
        Item item = this.itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        this.orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = this.orderRepository.findOne(orderId);
        order.cancel();
    }

    /* public List<Order> findOrders(OrderSearch orderSearch) {
        return this.orderRepository.findAll(orderSearch);
    }*/
}
