package datajpa.repository;

import datajpa.entity.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(findMember.getId())
                  .isEqualTo(member.getId());
        assertThat(findMember.getUsername())
                  .isEqualTo(member.getUsername());
        assertThat(findMember)
                  .isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        this.memberRepository.save(member1);
        this.memberRepository.save(member2);

        // 조회 검증
        Member findMember1 = this.memberRepository.findById(member1.getId()).get();
        Member findMember2 = this.memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 리스트 조회 검증
        List<Member> all = this.memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = this.memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        this.memberRepository.delete(member1);
        this.memberRepository.delete(member2);

        long deletedCount = this.memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThan() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        this.memberRepository.save(m1);
        this.memberRepository.save(m2);

        List<Member> result = this.memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void noOptionalTest() {
        Member m1 = new Member("AAA");
        this.memberRepository.save(m1);

        Optional<Member> optionalMember = this.memberRepository.findById(1L);
        Member noOptionalMember = this.memberRepository.findNoOptionalById(1L);
        assertThat(noOptionalMember).isEqualTo(optionalMember.get());
    }
}