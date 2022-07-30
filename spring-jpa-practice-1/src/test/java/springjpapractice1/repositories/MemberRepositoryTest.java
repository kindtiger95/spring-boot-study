package springjpapractice1.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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