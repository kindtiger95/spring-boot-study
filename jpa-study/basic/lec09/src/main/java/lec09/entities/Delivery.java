package lec09.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity {
    public Delivery() {
    }

    private DeliveryStatus deliveryStatus;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery")
    private Orders orders;
}
