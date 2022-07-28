package springjpapractice1.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.Member;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Test
    @Transactional // @Test Annotation에 있으면 자동으로 rollback한다.
    // @Rollback(value = false)
    void save() {
    }

    @Test
    void find() {
    }
}