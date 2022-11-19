/*
package springbasicpractice.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springbasicpractice.AppConfig;
import springbasicpractice.commons.Grade;
import springbasicpractice.commons.Member;
import springbasicpractice.commons.Order;
import springbasicpractice.services.Impl.MemberServiceImpl;
import springbasicpractice.services.Impl.OrderServiceImpl;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}*/
