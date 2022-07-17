package hellojpa.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    public Movie() {}

    private String director;
    private String actor;
}
