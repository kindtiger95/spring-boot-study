package springbasicpractice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springbasicpractice.common.enumeration.Member;
import springbasicpractice.common.enumeration.Order;
import springbasicpractice.repository.MemberRepository;
import springbasicpractice.service.OrderService;
import springbasicpractice.service.discount.DiscountPolicy;

@Primary
@Component
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = this.memberRepository.findById(memberId);
        int discountPrice = this.discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
