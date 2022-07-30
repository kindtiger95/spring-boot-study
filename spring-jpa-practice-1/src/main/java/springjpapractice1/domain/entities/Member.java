package springjpapractice1.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded private Address address;

    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();
}
