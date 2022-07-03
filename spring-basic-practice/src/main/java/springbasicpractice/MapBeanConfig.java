package springbasicpractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springbasicpractice.services.discount.DiscountPolicy;
import springbasicpractice.services.discount.Impl.FixDiscountPolicy;
import springbasicpractice.services.discount.Impl.RateDiscountPolicy;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class MapBeanConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean DiscountPolicy fixDiscountPolicy() {
        return new FixDiscountPolicy();
    }
}
