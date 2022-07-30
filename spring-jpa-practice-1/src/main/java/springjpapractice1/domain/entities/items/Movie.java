package springjpapractice1.domain.entities.items;

import lombok.*;
import springjpapractice1.domain.entities.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("movie")
public class Movie extends Item {
    private String director;
    private String actor;
}
