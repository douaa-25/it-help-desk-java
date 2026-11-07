package tn.ithelpdesk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable=false, length=140)
    private String title;
    @NotBlank @Column(nullable=false, length=4000)
    private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20)
    private TicketStatus status = TicketStatus.NEW;
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private AppUser user;
    @ManyToOne(optional=false)
    @JoinColumn(name="category_id")
    private Category category;
    @Column(nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Ticket() {}
    public Ticket(String title, String description, AppUser user, Category category) {
        this.title=title; this.description=description; this.user=user; this.category=category;
        this.status=TicketStatus.NEW; this.createdAt=LocalDateTime.now();
    }
    public Long getId(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public TicketStatus getStatus(){return status;}
    public AppUser getUser(){return user;}
    public Category getCategory(){return category;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setStatus(TicketStatus v){status=v;}
}
