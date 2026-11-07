package tn.ithelpdesk.service;

import tn.ithelpdesk.model.*;
import tn.ithelpdesk.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository tickets;
    private final UserRepository users;
    private final CategoryRepository categories;

    public TicketService(TicketRepository tickets, UserRepository users, CategoryRepository categories) {
        this.tickets=tickets; this.users=users; this.categories=categories;
    }

    public List<Ticket> findForUser(String username, boolean admin) {
        return admin ? tickets.findAllByOrderByCreatedAtDesc()
                     : tickets.findByUserUsernameOrderByCreatedAtDesc(username);
    }

    @Transactional
    public Ticket create(String username, String title, String description, Long categoryId) {
        AppUser user=users.findByUsername(username).orElseThrow();
        Category category=categories.findById(categoryId).orElseThrow();
        return tickets.save(new Ticket(title.trim(), description.trim(), user, category));
    }

    @Transactional
    public void updateStatus(Long id, TicketStatus status) {
        Ticket ticket=tickets.findById(id).orElseThrow();
        ticket.setStatus(status);
    }

    @Transactional
    public void cancelOwnTicket(Long id, String username) {
        Ticket ticket=tickets.findById(id).orElseThrow();
        if (!ticket.getUser().getUsername().equals(username))
            throw new org.springframework.security.access.AccessDeniedException("Accès interdit");
        if (ticket.getStatus()==TicketStatus.NEW) ticket.setStatus(TicketStatus.CANCELLED);
    }
}
