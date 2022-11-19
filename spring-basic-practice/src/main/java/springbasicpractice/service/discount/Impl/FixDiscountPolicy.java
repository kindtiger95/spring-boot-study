package springbasicpractice.service.discount.Impl;

import org.springframework.stereotype.Component;
import springbasicpractice.common.enumeration.Grade;
import springbasicpractice.common.enumeration.Member;
import springbasicpractice.service.discount.DiscountPolicy;

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
