package practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.domain.Item;
import practice.domain.Member;
import practice.domain.Order;
import practice.domain.OrderSearch;
import practice.dto.OrderQueryDto;
import practice.repository.OrderQueryRepository;
import practice.service.ItemService;
import practice.service.MemberService;
import practice.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = this.memberService.findAll();
        List<Item> items = this.itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        this.orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = this.orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        this.orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

    @GetMapping(value = "/api/v4/orders")
    public @ResponseBody List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping(value = "/api/v5/orders")
    public @ResponseBody List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }
}
