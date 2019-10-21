package io.github.dgahn.jpashop.repository;

import io.github.dgahn.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager em;

  public void save(Item item) {
    if (item.getId() == null) { //  item_id가 null일 경우 신규
      em.persist(item);
    } else { // update와 비슷함.
      em.merge(item);
    }
  }

  public Item findOne(Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    return em.createQuery("SELECT i FROM Item i", Item.class)
             .getResultList();
  }

}
