package springjpapractice1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @RequiredArgsConstructor
    public static class InitService {
        private final EntityManager em;

        public void dbInit1() {
        }
    }
}
