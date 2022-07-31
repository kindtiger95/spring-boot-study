package springjpapractice1.domain.entities.items;

import lombok.*;
import springjpapractice1.domain.entities.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@DiscriminatorValue("book")
public class Book extends Item {
    private String author;
    private String isbn;
}



