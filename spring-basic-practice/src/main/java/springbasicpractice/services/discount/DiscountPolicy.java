package springbasicpractice.services.discount;

import springbasicpractice.commons.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
