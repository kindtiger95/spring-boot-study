package lec07.entities;

import javax.persistence.*;

@Entity
public class CategoryItem extends BaseEntity {
    public CategoryItem() {
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
