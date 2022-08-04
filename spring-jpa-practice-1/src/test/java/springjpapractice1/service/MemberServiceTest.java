package springjpapractice1.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.Address;
import springjpapractice1.domain.Member;
import springjpapractice1.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입_성공() {
        Member member = new Member();
        member.setAddress(new Address("도시", "거리", "11111"));
        member.setName("나");
        Long joinedMember = this.memberService.join(member);

        Member findMember = this.memberRepository.findById(member.getId());
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 회원가입_실패_중복회원() {
        Member member1 = new Member();
        member1.setAddress(new Address("도시", "거리", "11111"));
        member1.setName("나");
        Long joinedMember1 = this.memberService.join(member1);

        Member member2 = new Member();
        member2.setAddress(new Address("도시", "거리", "11111"));
        member2.setName("나");

        Assertions.assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> {
            Long joinedMember2 = this.memberService.join(member2);
        });
    }
}