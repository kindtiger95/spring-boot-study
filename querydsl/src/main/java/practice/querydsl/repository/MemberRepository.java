package practice.querydsl.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import practice.querydsl.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, QMemberRepository {
    Optional<Member> findByUsername(String username);
}
