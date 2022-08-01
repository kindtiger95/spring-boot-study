package springjpapractice1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springjpapractice1.domain.dto.OrderSearch;
import springjpapractice1.domain.entities.Item;
import springjpapractice1.domain.entities.Member;
import springjpapractice1.domain.entities.Order;
import springjpapractice1.services.ItemService;
import springjpapractice1.services.MemberService;
import springjpapractice1.services.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = this.memberService.findMembers();
        List<Item> items = this.itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("count") Long itemId, @RequestParam("count") int count) {
        this.orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = this.orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        this.orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
