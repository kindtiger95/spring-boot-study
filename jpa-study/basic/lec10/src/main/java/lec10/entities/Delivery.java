package lec10.entities;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {
    public Delivery() {
    }

    public Delivery(String city, String zipcode, DeliveryStatus deliveryStatus) {
        this.city = city;
        this.zipcode = zipcode;
        this.deliveryStatus = deliveryStatus;
    }

    private String city;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Orders orders;
}
