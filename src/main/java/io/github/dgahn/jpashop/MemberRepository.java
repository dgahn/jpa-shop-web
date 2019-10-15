package io.github.dgahn.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class MemberRepository {

  @PersistenceContext // 엔티티 매니저를 주입 시켜주는 애노테이션
  private EntityManager em;

  public Long save(Member member) {
    em.persist(member);
    return member.getId();
  }

  public Member find(Long id) {
    return em.find(Member.class, id);
  }

}
