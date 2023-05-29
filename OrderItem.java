package likelion.springbootBaco.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
//item 필드는 OrderItem과 Item 간의 다대일(N:1) 관계를 매핑한다.
// @ManyToOne 어노테이션을 사용하며, fetch = LAZY를 통해 지연 로딩을 설정한다
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
//   order 필드는 OrderItem과 Order 간의 다대일(N:1) 관계를 매핑한다.
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
//item 필드는 OrderItem과 Item 간의 다대일(N:1) 관계를 매핑합니다.
// @ManyToOne 어노테이션을 사용하며, fetch = LAZY를 통해 지연 로딩을 설정합니다.
    private Item item;

    private Integer price;
    private Integer count;

    /**
     * 스태틱 팩토리 메서드
     */
//  createOrderItem 메서드는 주문 상품을 생성하기 위한 정적 팩토리 메서드이다.
//  상품, 주문 가격, 주문 수량을 받아 OrderItem 인스턴스를 생성하고, 상품의 재고를 감소시킵니다.
    public static OrderItem createOrderItem(Item item, int orderPrice, int orderCount) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.price = orderPrice;
        orderItem.count = orderCount;
        // 연관관계 편의 메서드
        item.removeStock(orderCount);
        return orderItem;
    }

//   setOrder 메서드는 주문 상품과 주문 간의 연관 관계를 설정하고, 양방향 매핑
    public void setOrder(Order order) {
        this.order = order;
        order.getOrderItemList().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
        item.getOrderItem().add(this);
    }

    /**
     * 비즈니스 로직
     */
    public int getTotalPrice() {
        return this.getPrice() * this.getCount();
    }

    public void cancel() {
        this.getItem().addStock(count);
    }
}
