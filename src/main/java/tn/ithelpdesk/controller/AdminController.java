package tn.ithelpdesk.controller;

import tn.ithelpdesk.model.Category;
import tn.ithelpdesk.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminController {
    private final CategoryRepository categories;
    public AdminController(CategoryRepository categories){this.categories=categories;}

    @PostMapping
    public String add(@RequestParam String name) {
        String clean=name.trim();
        if (!clean.isBlank() && clean.length()<=80 && !categories.existsByNameIgnoreCase(clean))
            categories.save(new Category(clean));
        return "redirect:/dashboard";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        // La suppression est refusée si une catégorie est encore référencée par un ticket.
        try { categories.deleteById(id); } catch (Exception ignored) { }
        return "redirect:/dashboard";
    }
}
