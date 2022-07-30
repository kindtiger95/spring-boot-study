package springjpapractice1.domain.entities.items;

import lombok.Getter;
import springjpapractice1.domain.entities.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
@Getter
public class Book extends Item {
    private String author;
    private String isbn;
}
