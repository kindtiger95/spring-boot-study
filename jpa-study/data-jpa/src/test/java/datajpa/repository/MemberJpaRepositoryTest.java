package datajpa.repository;

import datajpa.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA");

        Member saveMember = this.memberJpaRepository.save(member);
        Member findMember = this.memberJpaRepository.find(saveMember.getId());

        Assertions.assertThat(findMember.getId())
                  .isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername())
                  .isEqualTo(member.getUsername());
        Assertions.assertThat(findMember)
                  .isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        this.memberJpaRepository.save(member1);
        this.memberJpaRepository.save(member2);

        Member findMember1 = this.memberJpaRepository.findById(member1.getId())
                                                     .get();
        Member findMember2 = this.memberJpaRepository.findById(member2.getId())
                                                     .get();
        Assertions.assertThat(findMember1)
                  .isEqualTo(member1);
        Assertions.assertThat(findMember2)
                  .isEqualTo(member2);

        List<Member> all = this.memberJpaRepository.findAll();
        Assertions.assertThat(all.size())
                  .isEqualTo(2);

        long count = this.memberJpaRepository.count();
        Assertions.assertThat(count)
                  .isEqualTo(2);

        this.memberJpaRepository.delete(member1);
        this.memberJpaRepository.delete(member2);

        long deletedCount = this.memberJpaRepository.count();
        Assertions.assertThat(deletedCount)
                  .isEqualTo(0);
    }
}