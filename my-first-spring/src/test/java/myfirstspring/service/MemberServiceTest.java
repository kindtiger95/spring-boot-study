package myfirstspring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import myfirstspring.entity.Member;
import myfirstspring.repository.MemoryMemberRepository;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        this.memberRepository.clearStore();
    }

    @Test
    void join() {
        Member member = new Member();
        member.setName("hello");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();

        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void findMembers() throws Exception {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("already exist.");
    }

    @Test
    void findOne() {
    }
}