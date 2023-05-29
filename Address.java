package likelion.springbootBaco.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//주소 정보를 나타내는 도메인 클래스이다.
@Embeddable
@Data
//@AllArgsConstructor는 모든 필드를 인자로 받는 생성자를 생성하며, @NoArgsConstructor는 인자 없는 기본 생성자를 생성한다.
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String city; //주소의 도시명
    private String state; //주소의 '주?'
    private String street; //주소의 거리명
    private String zipcode; //주소의 우편번호
    // Getter와 Setter 메소드는 Lombok의 @Data 어노테이션에 의해 자동 생성된다.
}
}


