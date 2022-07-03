package springbasicpractice.services.discount.Impl;

import org.springframework.stereotype.Component;
import springbasicpractice.commons.Grade;
import springbasicpractice.commons.Member;
import springbasicpractice.services.discount.DiscountPolicy;

@Component
public class FixDiscountPolicy implements DiscountPolicy {
    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return this.discountFixAmount;
        } else return 0;
    }
}
