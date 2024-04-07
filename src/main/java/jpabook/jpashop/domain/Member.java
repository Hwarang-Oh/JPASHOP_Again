package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // Consider as Primary Key
    private Long id;
    private String name;

    @Embedded // 내장 Type을 내장하고 있다는 의미의 Annotation -> 한 곳에만 쓰면 되지만, 둘 다 쓰는 것이 가독성 측면에서의 이득
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
