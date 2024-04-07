package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // Table 구분자
@Getter @Setter
public abstract class Item { // 구현체를 가질 것이기 때문에 추상 클래스로 구현

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //== Business Logic ==//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) {
        int resultStock = this.stockQuantity - quantity;
        if (resultStock < 0) throw new NotEnoughStockException("NEED MORE STOCK");
        this.stockQuantity = resultStock;
    }
}
