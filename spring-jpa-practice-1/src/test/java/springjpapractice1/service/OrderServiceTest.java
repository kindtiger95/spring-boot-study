package springjpapractice1.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.*;
import springjpapractice1.repository.OrderRepository;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EntityManager em;

    @Test
    void 상품주문() {
        Member member = createMember();
        Book book = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = this.orderService.order(member.getId(), book.getId(), orderCount);

        Order findOrder = this.orderRepository.findOne(orderId);

        Assertions.assertThat(findOrder.getStatus())
                  .isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(1).isEqualTo(findOrder.getOrderItems().size());
        Assertions.assertThat(10000 * 2).isEqualTo(findOrder.getTotalPrice());
        Assertions.assertThat(8).isEqualTo(book.getStockQuantity());
    }

    @Test
    void cancelOrder() {
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        this.em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        this.em.persist(book);
        return book;
    }
}