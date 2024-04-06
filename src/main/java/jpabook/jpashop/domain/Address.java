package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 다른 class에 내장될 수 있는 Type이라는 Annotation
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { // protected로 설계해서, 외부에서 접근을 막고 JPA 기능을 사용할 수 있는 기본 생성자
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    // Immutable하게 관리되어야 하는 Value Type을 관리하기 위해 Setter를 없애고, 생성자 설계
    // -> 기본 생성자가 사라지면서, JPA가 접근 불가
}
