package springbasicpractice.service;

import springbasicpractice.common.enumeration.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
