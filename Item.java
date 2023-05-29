package likelion.springbootBaco.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
//@Getter와 @Setter 어노테이션은 Lombok에서 제공하는 어노테이션으로, 모든 필드에 대한 게터와 세터 메소드를 생성합니다.
@Getter
@Setter
@NoArgsConstructor
//Item 클래스는 @Entity 어노테이션을 사용하여 JPA 엔티티임을 표시한다. 이는 해당 클래스가 데이터베이스 테이블과 매핑될 수 있음을 나타낸다
public class Item {
//    @Id와 @GeneratedValue 어노테이션은 해당 필드가 엔티티의 식별자(primary key)임을 나타내고, 자동 생성되도록 설정한다.
    @Id @GeneratedValue
    private Long id;
//@OneToMany(mappedBy = "item") 어노테이션은 Item 엔티티와 OrderItem 엔티티 사이의 일대다 관계를 매핑한다.
// mappedBy 속성은 OrderItem 엔티티에서 Item 엔티티를 참조하는 필드를 지정한다.
    @OneToMany(mappedBy = "item")
//    orderItem 필드는 Item과 OrderItem 간의 연관 관계를 표현하는데 사용된다.
    private List<OrderItem> orderItem = new ArrayList<>();

    private String brand;
    private String name;
    private Integer price;
    private Integer stock;

    /**
     * 비즈니스 로직
     */
    @Comment("재고 추가")
//   addStock() 메소드는 재고를 추가하는 비즈니스 로직을 나타내고 주어진 수량만큼 재고를 증가시킨다.
//removeStock() 메소드는 재고를 감소시키는 비즈니스 로직을 나타내고 주어진 수량만큼 재고를 감소시킨다.
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    @Comment("재고 감소")
    public void removeStock(int stockQuantity) {
        int restStock = this.stock - stockQuantity;
        if (restStock < 0) {
            throw new IllegalStateException("need more stock");
        }
        this.stock = restStock;
    }
}
