package springjpapractice1.domain.entities.items;

import lombok.Getter;
import springjpapractice1.domain.entities.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("movie")
public class Movie extends Item {
    private String director;
    private String actor;
}
