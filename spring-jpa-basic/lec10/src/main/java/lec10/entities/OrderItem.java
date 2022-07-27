package lec10.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderItem extends BaseEntity {
    public OrderItem() {
    }

    private Integer orderPrice;
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

}
