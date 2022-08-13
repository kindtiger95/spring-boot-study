package datajpa.repository;

import datajpa.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member saveMember = this.memberRepository.save(member);
        Member findMember = this.memberRepository.findById(saveMember.getId())
                                                 .get();
        Assertions.assertThat(findMember.getId())
                  .isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername())
                  .isEqualTo(member.getUsername());
        Assertions.assertThat(findMember)
                  .isEqualTo(member);
    }
}