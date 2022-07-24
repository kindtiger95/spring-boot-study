package lec04.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
    public Item() {}

    @Id
    private Long id;

    private String name;
    private Integer price;
    private Integer stockQuantity;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
