package likelion.springbootbbaco.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private String name;
    private Integer price;
    private Integer stock;//int는 아주 원시 데이터 전부다 class type으로 지정 repo class ex) integer

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItemList = new ArrayList<>();

    //비즈니스 로직
    public void addStock(int quantity){
        this.stock += quantity;
    }

    public void removestock(int quantity){
        int restStock = this.stock - quantity;
        if(restStock<8){
            throw new IllegalStateException("need more stock");
        }
        this.stock=restStock;
    }
}
