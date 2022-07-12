package hellojpa.jpashop.domain;

import javax.persistence.*;

@Entity(name = "order_item")
public class OrderItem {
    public OrderItem() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_price")
    private int orderPrice;

    private int count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
