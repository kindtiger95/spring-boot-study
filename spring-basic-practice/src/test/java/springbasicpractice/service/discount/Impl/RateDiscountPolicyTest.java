package springbasicpractice.service.discount.Impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springbasicpractice.common.enumeration.Grade;
import springbasicpractice.common.enumeration.Member;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("Rate Discount Policy Test (VIP).")
    void vip() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        int discount = this.rateDiscountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("Rate Discount Policy Test (Not VIP).")
    void no_vip() {
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
        int discount = this.rateDiscountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}