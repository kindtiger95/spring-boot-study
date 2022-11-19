package springbasicpractice.service.discount.Impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springbasicpractice.common.enumeration.Grade;
import springbasicpractice.common.enumeration.Member;
import springbasicpractice.service.discount.DiscountPolicy;

@Primary
@Component
public class RateDiscountPolicy implements DiscountPolicy {
    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP)
            return price * this.discountPercent / 100;
        else
            return 0;
    }
}
