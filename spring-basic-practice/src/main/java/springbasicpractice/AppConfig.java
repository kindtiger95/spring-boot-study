package springbasicpractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbasicpractice.repositories.MemberRepository;
import springbasicpractice.repositories.MemoryMemberRepository;
import springbasicpractice.services.Impl.MemberServiceImpl;
import springbasicpractice.services.Impl.OrderServiceImpl;
import springbasicpractice.services.MemberService;
import springbasicpractice.services.OrderService;
import springbasicpractice.services.discount.DiscountPolicy;
import springbasicpractice.services.discount.Impl.FixDiscountPolicy;
import springbasicpractice.services.discount.Impl.RateDiscountPolicy;

/*@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}*/
