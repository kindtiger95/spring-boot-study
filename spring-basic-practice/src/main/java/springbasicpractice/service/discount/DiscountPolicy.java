package springbasicpractice.service.discount;

import springbasicpractice.common.enumeration.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
