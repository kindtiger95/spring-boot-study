package hellojpa.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item {
    public Album() {}

    private String artist;
    private String etc;
}
