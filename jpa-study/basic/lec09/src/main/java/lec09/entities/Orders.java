package lec09.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Orders extends BaseEntity {
    public Orders() {}

    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
