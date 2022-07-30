package springjpapractice1.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.entities.Address;
import springjpapractice1.domain.entities.Item;
import springjpapractice1.domain.entities.Member;
import springjpapractice1.domain.entities.Order;
import springjpapractice1.domain.entities.items.Book;
import springjpapractice1.exceptions.NotEnoughStockException;
import springjpapractice1.repositories.OrderRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static springjpapractice1.commons.GlobalEnums.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = createMember();
        em.persist(member);

        Item book = createItem();
        em.persist(book);
        int orderCount = 2;

        // when
        Long orderId = this.orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order findOrder = this.orderRepository.findOne(orderId);
        assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getOrderItems()
                            .size()).isEqualTo(1);
        assertThat(findOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(8);
    }

    @Test
    public void 주문취소() throws Exception {
        // given
        Member member = createMember();
        em.persist(member);
        Item item = createItem();
        em.persist(item);

        int orderCount = 2;

        Long orderId = this.orderService.order(member.getId(), item.getId(), orderCount);
        // when
        this.orderService.cancelOrder(orderId);

        // then
        Order findOrder = this.orderRepository.findOne(orderId);

        assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        // given
        Member member = createMember();
        em.persist(member);

        Item book = createItem();
        em.persist(book);
        int orderCount = 11;

       // when
        assertThatExceptionOfType(NotEnoughStockException.class).isThrownBy(() -> {
            this.orderService.order(member.getId(), book.getId(), orderCount);

        });
        // then
    }

    private Member createMember() {
        return Member.builder()
                     .name("회원1")
                     .address(new Address("서울", "강가", "123-123"))
                     .build();
    }

    private Item createItem() {
        Item book = Book.builder()
                        .build();
        book.setName("JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        return book;
    }
}