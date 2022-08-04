package springjpapractice1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springjpapractice1.domain.Address;
import springjpapractice1.domain.Member;
import springjpapractice1.service.MemberService;

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

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = this.memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @PostMapping("/members/new")
    public String create(@Validated MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        this.memberService.join(member);
        return "redirect:/";
    }
}
