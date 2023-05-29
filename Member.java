package likelion.springbootBaco.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
//@NoArgsConstructor(access = PROTECTED) 어노테이션은 인자 없는 기본 생성자를 생성하며,
// 접근 제한자를 PROTECTED로 설정하여 직접 생성하지 못하도록 제한한다.
// 이는 외부에서 new Member()를 통한 생성을 막고, createMember() 정적 메소드를 사용하여 생성하도록 유도한다.
@NoArgsConstructor(access = PROTECTED)
public class Member {
//   @Id와 @GeneratedValue 어노테이션은 해당 필드가 엔티티의 식별자(primary key)임을 나타내고, 자동 생성되도록 설정한다.
    @Id @GeneratedValue
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "member") 어노테이션은 Member 엔티티와 Order 엔티티 사이의 일대다 관계를 매핑한다.
//    mappedBy 속성은 Order 엔티티에서 Member 엔티티를 참조하는 필드를 지정한다.
    @OneToMany(mappedBy = "member")
//    orderList 필드는 Member과 Order 간의 연관 관계를 표현하는데 사용된다
    private List<Order> orderList = new ArrayList<>();

//    @Embedded 어노테이션은 Address 클래스를 내장(embedded)해서 사용함을 나타낸다.
//    Address 필드는 Member 엔티티의 일부로 저장된다.
    @Embedded
    private Address address;

//    createMember() 메소드는 회원 객체를 생성하는 정적 메소드로, 이름과 주소를 인자로 받아 회원을 생성한다.
    public static Member createMember(String name, Address address) {
        Member member = new Member();
        member.name = name;
        member.address = address;
        return member;
    }
}
