package likelion.springbootBaco.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "orders") // 이거 안하면 에러
@Getter
@NoArgsConstructor(access = PROTECTED)
//Order 클래스는 orders 테이블과 매핑된다.
// @Table 어노테이션을 사용하여 테이블 이름을 "orders"로 지정합니다.
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
//   member 필드는 Order와 Member 간의 다대일(N:1) 관계를 매핑한다.
//   @ManyToOne 어노테이션을 사용하며, fetch = LAZY를 통해 지연 로딩을 설정한다.
    private Member member;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
//    delivery 필드는 Order와 Delivery 간의 일대일(1:1) 관계를 매핑합니다. @OneToOne 어노테이션을 사용하며,
//    fetch = LAZY를 통해 지연 로딩을 설정하고, cascade = ALL를 통해 모든 작업에 대해 연쇄 동작을 수행한다.
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = ALL)
//   orderItemList 필드는 Order와 OrderItem 간의 일대다(1:N) 관계를 매핑합니다. @OneToMany 어노테이션을 사용하며,
//   mappedBy 속성을 통해 반대쪽 엔티티의 필드에 의해 매핑되었음을 지정한다.
    private List<OrderItem> orderItemList = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // 연관관계 편의 메서드
//   setMember 메서드는 주문과 회원 간의 연관 관계를 설정하고, 양방향 매핑을 위해 회원 엔티티의 orderList에 현재 주문을 추가한.
    public void setMember(Member member) {
        this.member = member;
        member.getOrderList().add(this);
    }

//    createOrder 메서드는 주문을 생성하기 위한 정적 팩토리 메서드이다.
//    주문, 주문 일시, 주문 상태, 배송 정보 등을 설정하고, 주문 상품들에 대한 정보를 추가한다.
    public static Order createOrder(Member member, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.orderDate = LocalDateTime.now();
        order.orderStatus = OrderStatus.ORDERED;
        order.delivery = Delivery.createDelivery(order, member.getAddress().getCity(),
                member.getAddress().getState(),
                member.getAddress().getStreet(),
                member.getAddress().getZipcode());
        for (OrderItem orderItem : orderItems) {
            order.orderItemList.add(orderItem);
            orderItem.setOrder(order);
        }
        return order;
    }

//    cancel 메서드는 주문을 취소하는 동작을 수행한다.
//    배송 상태가 "완료"인 경우 예외를 던지고, 주문 상태를 "취소"로 변경하고, 주문에 속한 주문 상품들의 취소 동작을 수행한다.
    public void cancel() {
        if (delivery.getDeliveryStatus() == Delivery.DeliveryStatus.DONE) {
            throw new IllegalStateException("배송 완료했다 양아치야");
        }
        this.orderStatus = OrderStatus.CANCELED;
        for (OrderItem orderItem : orderItemList) {
            orderItem.cancel();
        }
    }

//    getTotalPrice 메서드는 주문에 포함된 주문 상품들의 총 가격을 계산하여 반환합니다.
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
