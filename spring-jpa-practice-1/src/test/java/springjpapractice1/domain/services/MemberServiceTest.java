package springjpapractice1.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.entities.Member;
import springjpapractice1.domain.repositories.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member1 = Member.builder()
                               .name("ib")
                               .build();
        Member member2 = Member.builder()
                               .name("youk")
                               .build();

        // when
        Long member1Id = this.memberService.join(member1);
        Long member2Id = this.memberService.join(member2);

        System.out.println("member1Id = " + member1Id);
        System.out.println("member2Id = " + member2Id);
        // then
        assertThat(this.memberService.findOne(member1Id)
                                     .getId()).isEqualTo(member1Id);
        assertThat(this.memberService.findOne(member2Id)
                                     .getId()).isEqualTo(member2Id);

    }

}