package springjpapractice1.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;
import static springjpapractice1.commons.GlobalEnums.DeliveryStatus;
import static springjpapractice1.commons.GlobalEnums.OrderStatus;

@Entity
@Table(name = "orders")
@Builder
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 생성 메서드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = Order.builder()
                           .member(member)
                           .delivery(delivery)
                           .orderStatus(OrderStatus.ORDER)
                           .orderDate(LocalDateTime.now())
                           .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    // 비즈니스 로직
    public void cancel() {
        if (this.delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 완료된 상품입니다.");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
    }

    // 조회 로직
    public int getTotalPrice() {
        return this.orderItems.stream()
                              .mapToInt(OrderItem::getTotalPrice)
                              .sum();
    }
}
