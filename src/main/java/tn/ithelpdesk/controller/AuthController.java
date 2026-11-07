package tn.ithelpdesk.controller;

import tn.ithelpdesk.model.*;
import tn.ithelpdesk.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthController {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    public AuthController(UserRepository users, PasswordEncoder encoder){this.users=users;this.encoder=encoder;}

    public static class RegisterForm {
        @NotBlank @Size(min=3,max=50) private String username;
        @NotBlank @Email private String email;
        @NotBlank @Size(min=8) private String password;
        public String getUsername(){return username;} public void setUsername(String v){username=v;}
        public String getEmail(){return email;} public void setEmail(String v){email=v;}
        public String getPassword(){return password;} public void setPassword(String v){password=v;}
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterForm form, BindingResult result, Model model) {
        if (users.existsByUsername(form.username)) result.rejectValue("username","duplicate","Nom déjà utilisé");
        if (users.existsByEmail(form.email)) result.rejectValue("email","duplicate","Email déjà utilisé");
        if (result.hasErrors()) return "register";
        users.save(new AppUser(form.username,form.email,encoder.encode(form.password),Role.USER));
        return "redirect:/login?registered";
    }
}
