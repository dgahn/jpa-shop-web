package io.github.dgahn.jpashop.service;

import io.github.dgahn.jpashop.domain.Member;
import io.github.dgahn.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.fail;


@RunWith(SpringRunner.class) // junit에게 스프링과 함께 사용한다고 알려
@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  EntityManager em;

  @Test
  public void 회원가입() throws Exception {
    //given
    Member member = new Member();
    member.setName("kim");

    //when
    Long savedId = memberService.join(member);

    //then
    Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId)); // 같은 트랜잭션 안에서 처리하는 것이기 때문에 같은 것이다.
  }

  @Test(expected = IllegalStateException.class)
  public void 중복_회원_예외() throws Exception {
    //given
    Member member1 = new Member();
    member1.setName("kim");

    Member member2 =  new Member();
    member2.setName("kim");

    //when
    memberService.join(member1);
    memberService.join(member2);

    //then
    fail("예외가 발생해야 한다.");
  }

}