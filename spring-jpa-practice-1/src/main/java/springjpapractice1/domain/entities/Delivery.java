package springjpapractice1.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static springjpapractice1.commons.GlobalEnums.DeliveryStatus;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
}
