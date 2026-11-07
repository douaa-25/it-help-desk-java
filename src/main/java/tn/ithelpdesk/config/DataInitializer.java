package tn.ithelpdesk.config;

import tn.ithelpdesk.model.*;
import tn.ithelpdesk.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(UserRepository users, CategoryRepository categories, PasswordEncoder encoder) {
        return args -> {
            if (!users.existsByUsername("admin"))
                users.save(new AppUser("admin","admin@helpdesk.local",encoder.encode("Admin123!"),Role.ADMIN));
            if (!users.existsByUsername("testuser"))
                users.save(new AppUser("testuser","testuser@helpdesk.local",encoder.encode("User123!"),Role.USER));
            for (String name : new String[]{"Hardware","Software","Internet/Network","Printers","Other"})
                if (!categories.existsByNameIgnoreCase(name)) categories.save(new Category(name));
        };
    }
}
