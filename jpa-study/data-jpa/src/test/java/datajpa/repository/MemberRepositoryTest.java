package datajpa.repository;

import datajpa.entity.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
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
    public void 옵셔널과비옵셔널비교() {
        Member m1 = new Member("AAA");
        this.memberRepository.save(m1);

        Optional<Member> optionalMember = this.memberRepository.findById(1L);
        Member noOptionalMember = this.memberRepository.findNoOptionalById(1L);
        assertThat(noOptionalMember).isEqualTo(optionalMember.get());
    }

    @Test
    public void 페이징_연습() {
        this.add_member();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Direction.DESC, "username"));
        Page<Member> page = this.memberRepository.findByAge(10, pageRequest);

        // 조회된 데이터
        List<Member> content = page.getContent();
        assertThat(content.size()).isEqualTo(10);
        assertThat(page.getTotalElements()).isEqualTo(20);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    public void 벌크_쿼리() {
        this.memberRepository.save(new Member("member1", 10));
        this.memberRepository.save(new Member("member2", 19));
        this.memberRepository.save(new Member("member3", 20));
        this.memberRepository.save(new Member("member4", 21));
        this.memberRepository.save(new Member("member5", 40));

        int resultCount = this.memberRepository.bulkAgePlus(20);
        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    @Rollback(value = false)
    public void Auditing_테스트() {
        this.memberRepository.save(new Member("member1", 16));
        this.memberRepository.save(new Member("member2", 10));
        this.memberRepository.save(new Member("member3", 12));
        this.memberRepository.save(new Member("member4", 7));
        this.memberRepository.save(new Member("member5", 13));
        this.memberRepository.save(new Member("member6", 5));
    }

    private void add_member() {
        for (int i = 0; i < 20; i++) {
            this.memberRepository.save(new Member("member" + i, 10));
        }
    }
}