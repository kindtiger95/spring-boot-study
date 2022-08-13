package datajpa.repository;

import datajpa.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {
    private final EntityManager entityManager;

    public Member save(Member member) {
        this.entityManager.persist(member);
        return member;
    }

    public void delete(Member member) {
        this.entityManager.remove(member);
    }

    public List<Member> findAll() {
        String query = "SELECT m FROM Member AS m";
        return this.entityManager.createQuery(query, Member.class).getResultList();
    }

    public Optional<Member> findById(Long id) {
        Member member = this.entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count() {
        String query = "SELECT COUNT(m) FROM Member AS m";
        return this.entityManager.createQuery(query, Long.class)
                                              .getSingleResult();
    }

    public Member find(Long id) {
        return this.entityManager.find(Member.class, id);
    }

}
