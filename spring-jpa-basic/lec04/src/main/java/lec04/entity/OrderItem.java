package lec04.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItem {
    public OrderItem() {}

    @Id
    private Long id;

    private Long ordersId;
    private Long itemsId;
    private Integer orderPrice;
    private Integer count;

    public Long getId() {
        return id;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
