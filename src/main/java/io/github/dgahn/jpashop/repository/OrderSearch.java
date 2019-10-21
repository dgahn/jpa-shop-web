package io.github.dgahn.jpashop.repository;

import io.github.dgahn.jpashop.domain.Order;
import io.github.dgahn.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter @Setter
public class OrderSearch {

  private String memberName;
  private OrderStatus orderStatus;

}
