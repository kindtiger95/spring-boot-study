package springjpapractice1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springjpapractice1.domain.entities.Address;
import springjpapractice1.domain.entities.Member;
import springjpapractice1.domain.entities.items.Book;
import springjpapractice1.services.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = Member.builder()
                             .name(form.getName())
                             .address(address)
                             .build();
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = this.memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


}
