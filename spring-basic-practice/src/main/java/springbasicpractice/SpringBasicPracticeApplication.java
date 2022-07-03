package springbasicpractice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbasicpractice.commons.Grade;
import springbasicpractice.commons.Member;
import springbasicpractice.commons.Order;
import springbasicpractice.services.Impl.MemberServiceImpl;
import springbasicpractice.services.Impl.OrderServiceImpl;
import springbasicpractice.services.MemberService;
import springbasicpractice.services.OrderService;

@SpringBootApplication
public class SpringBasicPracticeApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		MemberService memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderServiceImpl", OrderService.class);

		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 10000);
		System.out.println("order = " + order);
	}
}
