package tn.ithelpdesk.repository;

import tn.ithelpdesk.model.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @EntityGraph(attributePaths = {"user", "category"})
    List<Ticket> findByUserUsernameOrderByCreatedAtDesc(String username);

    @EntityGraph(attributePaths = {"user", "category"})
    List<Ticket> findAllByOrderByCreatedAtDesc();
}
