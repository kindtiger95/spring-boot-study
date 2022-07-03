package springbasicpractice.services;

import springbasicpractice.commons.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
