package io.github.dgahn.jpashop.service;

import io.github.dgahn.jpashop.domain.Delivery;
import io.github.dgahn.jpashop.domain.Member;
import io.github.dgahn.jpashop.domain.Order;
import io.github.dgahn.jpashop.domain.OrderItem;
import io.github.dgahn.jpashop.domain.item.Item;
import io.github.dgahn.jpashop.repository.ItemRepository;
import io.github.dgahn.jpashop.repository.MemberRepository;
import io.github.dgahn.jpashop.repository.OrderRepository;
import io.github.dgahn.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;

  /**
   * 주문
   */
  @Transactional
  public Long order(Long memberId, Long itemId, int count) {
    // 엔티티 조
    Member member = memberRepository.findOne(memberId);
    Item item = itemRepository.findOne(itemId);

    //배송정보 생성
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());

    //주문 상품 생성회
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

    //주문 생성
    Order order = Order.createOrder(member, delivery, orderItem);

    //주문 저장
    orderRepository.save(order);

    return order.getId();
  }

  // 취소
  @Transactional
  public void cancelOrder(Long orderId) {
    // 주문 엔티티 조회
    Order order = orderRepository.findOne(orderId);
    // 주문 취소
    order.cancel();
  }

  // 검색
  public List<Order> findOrders(final OrderSearch orderSearch) {
    return orderRepository.findAll(orderSearch);
  }

}
