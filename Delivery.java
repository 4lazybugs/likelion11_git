package likelion.springbootBaco.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static likelion.springbootBaco.domain.Delivery.DeliveryStatus.ESTABLISHED;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
/*@NoArgsConstructor(access = PROTECTED) 어노테이션은 인자 없는 기본 생성자를 생성하며,
접근 제한자를 PROTECTED로 설정하여 직접 생성하지 못하도록 제한합니다. 이는 외부에서 new Delivery()를 통한 생성을 막고,
 createDelivery() 정적 메소드를 사용하여 생성하도록 유도한다*/
@Getter
/*Delivery 클래스는 @Entity 어노테이션을 사용하여 JPA 엔티티임을 표시한다.
이는 해당 클래스가 데이터베이스 테이블과 매핑될 수 있음을 나타낸다.
*/
public class Delivery {
    //@Id와 @GeneratedValue 어노테이션은 해당 필드가 엔티티의 식별자(primary key)임을 나타내고, 자동 생성되도록 설정한다.
    @Id @GeneratedValue
    private Long id;

//@OneToOne(mappedBy = "delivery") 어노테이션은 Delivery 엔티티와 Order 엔티티 사이의 일대일 관계를 매핑한다.
// mappedBy 속성은 Order 엔티티에서 Delivery 엔티티를 참조하는 필드를 지정한다.
    @OneToOne(mappedBy = "delivery")
    private Order order;

//    @Enumerated(STRING) 어노테이션은 deliveryStatus 필드가 Enum 타입이며, 데이터베이스에서 문자열로 저장되어야 함을 나타낸다.
    @Enumerated(STRING)
    private DeliveryStatus deliveryStatus;

    private String city;
    private String state;
    private String street;
    private String zipcode;

    public static Delivery createDelivery(Order order, String city, String state, String street, String zipcode) {
        Delivery delivery = new Delivery();
        delivery.order = order;
        delivery.deliveryStatus = ESTABLISHED;
        delivery.city = city;
        delivery.state = state;
        delivery.street = street;
        delivery.zipcode = zipcode;
        return delivery;
    }

    public enum DeliveryStatus {
        ESTABLISHED, PROGRESS, DONE
    }
}
