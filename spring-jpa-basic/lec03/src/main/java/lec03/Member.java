package lec03;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    public Member() {}
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}