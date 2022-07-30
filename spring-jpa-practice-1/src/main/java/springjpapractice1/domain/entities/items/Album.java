package springjpapractice1.domain.entities.items;

import lombok.Getter;
import springjpapractice1.domain.entities.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("album")
public class Album extends Item {
    private String artist;
    private String etc;
}
