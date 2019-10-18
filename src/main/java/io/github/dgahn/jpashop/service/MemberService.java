package io.github.dgahn.jpashop.service;

import io.github.dgahn.jpashop.domain.Member;
import io.github.dgahn.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true) // public 메소드마다 알아서 적용됨. // 읽기 전용으로 최적화 해준다.
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  // 회원 가입
  @Transactional
  public Long join(Member member) {
    validateDuplicateMember(member); // 중복 회원 검증
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(final Member member) {
    // 예외
    List<Member> findMembers = memberRepository.findByName(member.getName());
    if(!findMembers.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  // 회원 전체 조회
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }


  public Member findOne(Long memberId) {
    return memberRepository.findOne(memberId);
  }
}
