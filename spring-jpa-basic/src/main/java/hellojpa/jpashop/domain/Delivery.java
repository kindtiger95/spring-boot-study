package hellojpa.jpashop.domain;

import javax.persistence.*;

@Entity(name = "delivery")
public class Delivery extends BaseEntity {
    public Delivery() {
    }

    @OneToOne(mappedBy = "delivery")
    private Order order;
    private String street;
    private String zipcode;
    private String city;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
