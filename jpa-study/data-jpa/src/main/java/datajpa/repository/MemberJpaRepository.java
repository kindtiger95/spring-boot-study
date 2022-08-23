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
        return this.entityManager.createQuery(query, Member.class)
                                 .getResultList();
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

    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        String query = "SELECT m FROM Member AS m WHERE m.username = :username and m.age > :age";
        return this.entityManager.createQuery(query, Member.class)
                                 .setParameter("username", username)
                                 .setParameter("age", age)
                                 .getResultList();
    }

    // 기본 JPA 페이징 처리
    public List<Member> findByPage(int age, int offset, int limit) {
        String query = "SELECT m FROM Member AS m WHERE m.age = :age ORDER BY m.username DESC";
        return this.entityManager.createQuery(query, Member.class)
                                 .setParameter("age", age)
                                 .setFirstResult(offset)
                                 .setMaxResults(limit)
                                 .getResultList();
    }

    public Long totalCount(int age) {
        String query = "SELECT COUNT(m) FROM Member AS m WHERE m.age = :age";
        return this.entityManager.createQuery(query, Long.class)
                                 .setParameter("age", age)
                                 .getSingleResult();
    }

    // 벌크 쿼리
    public int bulkAgePlus(int age) {
        String query = "UPDATE Member AS m SET m.age = m.age + 1 WHERE m.age >= :age";
        return this.entityManager.createQuery(query)
                                 .setParameter("age", age)
                                 .executeUpdate();
    }
}
