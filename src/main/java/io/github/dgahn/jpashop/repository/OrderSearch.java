package io.github.dgahn.jpashop.repository;

import io.github.dgahn.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class OrderSearch {

  private String memberName;
  private OrderStatus orderStatus;
}
