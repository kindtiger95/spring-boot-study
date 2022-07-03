/*
package springbasicpractice.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springbasicpractice.commons.Grade;
import springbasicpractice.commons.Member;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AutoAppConfig appConfig = new AutoAppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findMember() {
    }
}*/
