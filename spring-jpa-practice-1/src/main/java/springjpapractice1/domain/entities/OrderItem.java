package springjpapractice1.domain.entities;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer orderPrice;
    private Integer count;

    public void cancel() {
        this.getItem().addStock(this.count);
    }

    public int getTotalPrice() {
        return this.orderPrice * this.count;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
                                   .item(item)
                                   .orderPrice(orderPrice)
                                   .count(count)
                                   .build();
        item.remove(count);
        return orderItem;
    }
}
