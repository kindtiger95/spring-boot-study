package springjpapractice1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        this.initService.dbInit1();
        this.initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Book book1 = createBook("SPRING1", 20000, 20);
            em.persist(book1);

            Book book2 = createBook("SPRING2", 40000, 300);
            em.persist(book2);

            Member member1 = em.find(Member.class, 1L);

            Book jpa1 = createBook("JPA1", 10000, 10);
            Book jpa2 = createBook("JPA2", 30000, 200);

            em.persist(jpa1);
            em.persist(jpa2);

            Delivery delivery = createDelivery(member);
            Delivery delivery1 = createDelivery(member1);

            OrderItem orderItem3 = OrderItem.createOrderItem(jpa1, 10000, 5);
            OrderItem orderItem4 = OrderItem.createOrderItem(jpa2, 30000, 100);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            Order order1 = Order.createOrder(member1, delivery1, orderItem3, orderItem4);

            em.persist(order1);
            em.persist(order);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

    }
}
