package tn.ithelpdesk.controller;

import tn.ithelpdesk.model.*;
import tn.ithelpdesk.repository.*;
import tn.ithelpdesk.service.TicketService;
import jakarta.validation.constraints.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {
    private final TicketService service;
    private final CategoryRepository categories;
    private final TicketRepository tickets;
    public TicketController(TicketService service, CategoryRepository categories, TicketRepository tickets) {
        this.service=service; this.categories=categories; this.tickets=tickets;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        boolean admin=auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("tickets",service.findForUser(auth.getName(),admin));
        model.addAttribute("isAdmin",admin);
        model.addAttribute("username",auth.getName());
        return "dashboard";
    }

    @GetMapping("/tickets/new")
    public String newTicket(Model model) {
        model.addAttribute("categories",categories.findAll());
        return "ticket-form";
    }

    @PostMapping("/tickets")
    public String create(@RequestParam @NotBlank @Size(max=140) String title,
                         @RequestParam @NotBlank @Size(max=4000) String description,
                         @RequestParam Long categoryId, Authentication auth) {
        service.create(auth.getName(),title,description,categoryId);
        return "redirect:/dashboard?created";
    }

    @PostMapping("/tickets/{id}/cancel")
    public String cancel(@PathVariable Long id, Authentication auth) {
        service.cancelOwnTicket(id,auth.getName());
        return "redirect:/dashboard";
    }

    @PostMapping("/admin/tickets/{id}/status")
    public String status(@PathVariable Long id, @RequestParam TicketStatus status) {
        service.updateStatus(id,status);
        return "redirect:/dashboard?updated";
    }
}
