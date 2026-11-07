package tn.ithelpdesk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Email @NotBlank @Column(nullable = false, unique = true, length = 120)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private Role role = Role.USER;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public AppUser() {}
    public AppUser(String username, String email, String password, Role role) {
        this.username=username; this.email=email; this.password=password; this.role=role;
    }
    public Long getId(){return id;}
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public Role getRole(){return role;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setUsername(String v){username=v;}
    public void setEmail(String v){email=v;}
    public void setPassword(String v){password=v;}
    public void setRole(Role v){role=v;}
}
