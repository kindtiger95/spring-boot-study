package springjpapractice1.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springjpapractice1.domain.entities.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        String query = "SELECT m FROM Member m";
        return em.createQuery(query, Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        String query = "SELECT m FROM Member as m WHERE m.name = :name";
        return em.createQuery(query, Member.class)
                 .setParameter("name", name)
                 .getResultList();
    }
}
