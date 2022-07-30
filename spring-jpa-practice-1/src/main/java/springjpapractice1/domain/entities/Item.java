package springjpapractice1.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springjpapractice1.exceptions.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer stockQuantity;

    public int addStock(int quantity) {
        this.stockQuantity += quantity;
        return this.stockQuantity;
    }

    public void remove(int quantity) {
        int retStock = this.stockQuantity - quantity;
        if (retStock < 0)
            throw new NotEnoughStockException("재고 부족합니다.");
    }
}
