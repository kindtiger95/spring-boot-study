package datajpa.repository;

import datajpa.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 다음은 2개다 된다. -> Optional을 사용하는 것이 더 나음.
    Optional<Member> findById(Long id);
    Member findNoOptionalById(Long id);

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // 위에꺼 보단 다음이 더 유용하다.
    @Query("SELECT m FROM Member AS m WHERE m.username = :username AND m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 컬렉션 타입은 다음과 같이 사용 -> 조건이 여러개일때
    @Query("SELECT m FROM Member AS m WHERE m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    // 스프링 데이터 JPA 페이징 예시


}
