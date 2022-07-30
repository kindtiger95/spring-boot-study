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
@DiscriminatorValue("album")
public class Album extends Item {
    private String artist;
    private String etc;
}
