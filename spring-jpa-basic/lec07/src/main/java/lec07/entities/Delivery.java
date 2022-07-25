package lec07.entities;

import javax.persistence.*;

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
