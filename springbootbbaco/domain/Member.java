package likelion.springbootbbaco.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private String city;
    private String address;
    @OneToMany(mappedBy="member")
    private List<Order> orderList= new ArrayList<>();  //객체를 생성하지 않고도 메서드를 뽑아낼수 잇는게 static

    public static Member createMember(String name, String address){
        Member member = new Member();
        member.name = name;
        member.address = address;
        return member;
    }

}


