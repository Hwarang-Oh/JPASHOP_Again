package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // Transaction 안에서, Data 변경이 되어야 한다. ( Spring Transaction Annotation 사용하기 )
@RequiredArgsConstructor // final이 존재하는 field만 생성자 주입을 만들어준다.
public class MemberService {

//    @Autowired // Field Injection -> 생성자 Injection으로 바꾸기 + 생성자 1개이므로 @AutoWired 생략 가능
    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    } => lombok으로 대체 가능

    /*
    * 회원 가입
     */
    @Transactional
    public Long join(Member member) { // void로 설계를 하지 않은 이유, 결과값을 보고자 하는 의도가 담겨 있음
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) throw new IllegalStateException("이미 존재하는 회원입니다.");
    } // 완벽한 Validation이 아님. => 동시성 문제가 존재함 -> Unique 제약 조건이 필요하긴 하다.

    // 회원 전체 조회
    public List<Member> findMembers() {
        return  memberRepository.findAll();
    }

    // 회원 단일 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
