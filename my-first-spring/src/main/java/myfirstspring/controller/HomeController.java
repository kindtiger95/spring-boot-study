package springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springbootstudy.service.MemberService;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
