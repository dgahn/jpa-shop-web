package io.github.dgahn.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;


@Embeddable
@Getter
public class Address {

  private String city;
  private String street;
  private String zipcode;

  protected Address() { } // 기본 생성자를 생성하고 접근 제한자를 protected까지 할 수 있다.

  public Address(final String city, final String street, final String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }

}
