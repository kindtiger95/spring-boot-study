package springjpapractice1.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springjpapractice1.domain.Address;
import springjpapractice1.domain.Order;
import springjpapractice1.domain.OrderItem;
import springjpapractice1.domain.OrderStatus;
import springjpapractice1.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = this.orderRepository.findAllWithOrderItems();
        return orders.stream()
                     .map(OrderDto::new)
                     .collect(Collectors.toList());
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV31(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = this.orderRepository.findAllWithBatchSize(offset, limit);
        return orders.stream()
                     .map(OrderDto::new)
                     .collect(Collectors.toList());
    }

    @Getter
    public static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItemsDto;

        public OrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember()
                             .getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery()
                                .getAddress();
            this.orderItemsDto = order.getOrderItems()
                                      .stream()
                                      .map(OrderItemDto::new)
                                      .collect(Collectors.toList());
        }
    }

    @Getter
    public static class OrderItemDto {
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            this.itemName = orderItem.getItem()
                                     .getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
