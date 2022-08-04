package springjpapractice1.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springjpapractice1.domain.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member) {
        this.em.persist(member);
        return member.getId();
    }

    public List<Member> findAll() {
        String query = "SELECT m FROM Member m";
        return this.em.createQuery(query, Member.class)
                      .getResultList();
    }

    public Member findById(Long id) {
        return this.em.find(Member.class, id);
    }

    public Member findByName(String name) {
        String query = "SELECT m FROM Member m WHERE m.name = :name";
        return this.em.createQuery(query, Member.class)
                      .setParameter("name", name)
                      .getResultList()
                      .stream()
                      .findAny()
                      .orElseGet(Member::new);
    }
}
