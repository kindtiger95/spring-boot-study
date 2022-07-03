package springbasicpractice.services.discount.Impl;

import org.springframework.stereotype.Component;
import springbasicpractice.commons.Grade;
import springbasicpractice.commons.Member;
import springbasicpractice.services.discount.DiscountPolicy;

@Component("discountPolicy")
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
