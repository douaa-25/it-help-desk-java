package tn.ithelpdesk.service;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.ithelpdesk.model.*;
import tn.ithelpdesk.repository.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {
    @Mock TicketRepository tickets;
    @Mock UserRepository users;
    @Mock CategoryRepository categories;

    @Test
    void userTicketListMustNotBeGlobalForRegularUser() {
        MockitoAnnotations.openMocks(this);
        TicketService service = new TicketService(tickets,users,categories);
        service.findForUser("testuser",false);
        verify(tickets).findByUserUsernameOrderByCreatedAtDesc("testuser");
        verify(tickets,never()).findAllByOrderByCreatedAtDesc();
    }

    @Test
    void adminCanRequestAllTickets() {
        MockitoAnnotations.openMocks(this);
        TicketService service = new TicketService(tickets,users,categories);
        service.findForUser("admin",true);
        verify(tickets).findAllByOrderByCreatedAtDesc();
    }
}
