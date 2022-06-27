package springbootstudy;

import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbootstudy.repository.JpaMemberRepository;
import springbootstudy.repository.MemberRepository;
import springbootstudy.repository.MemoryMemberRepository;
import springbootstudy.service.MemberService;

@Configuration
public class SpringConfig {

    private final EntityManager entityManager;

    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(entityManager);
    }
}
