package tn.ithelpdesk.controller;

import tn.ithelpdesk.controller.AuthController.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/") public String home(){ return "redirect:/dashboard"; }
    @GetMapping("/login") public String login(){ return "login"; }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }
}
