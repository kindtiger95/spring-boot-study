package lec08.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// Discriminate Column 이름을 바꾸고 싶으면
@DiscriminatorValue("changeNameBook")
public class Book extends Item {
    public Book() {
    }

    private String author;
    private Boolean isBn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getBn() {
        return isBn;
    }

    public void setBn(Boolean bn) {
        isBn = bn;
    }
}

