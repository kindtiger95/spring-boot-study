package lec07.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity {
    public Delivery() {
    }

    private String city;
    private String zipcode;
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery")
    private Orders orders;
}
