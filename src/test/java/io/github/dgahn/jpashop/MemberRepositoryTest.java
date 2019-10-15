package io.github.dgahn.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @Test
  @Transactional // 트랜잭션이 들어가 있는 테스트 케이스는 스프링이 테스트를 진행하고 나서 데이터베이스를 롤백한다.
  @Rollback(false) // 롤백을 막을 수 있다.
  public void testMember() throws Exception {
    //given
    Member member = new Member();
    member.setUsername("MemberA");

    //when
    Long saveId = memberRepository.save(member);
    Member findMember = memberRepository.find(saveId);

    //then
    Assertions.assertThat(findMember.getId())
              .isEqualTo(member.getId());
    Assertions.assertThat(findMember.getUsername())
              .isEqualTo(member.getUsername());
    Assertions.assertThat(findMember).isEqualTo(member);
  }

}