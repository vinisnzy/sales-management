package com.vinisnzy.sales_management.config;

import com.vinisnzy.sales_management.enums.UserRole;
import com.vinisnzy.sales_management.model.User;
import com.vinisnzy.sales_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminInitializer {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Bean
    public CommandLineRunner initAdmin(UserRepository repository) {
        return args -> {
            if (repository.findByEmail(adminEmail).isEmpty()) {
                String encryptedPassword = new BCryptPasswordEncoder().encode(adminPassword);
                User admin = new User(null, "admin", adminEmail, encryptedPassword, UserRole.ADMIN);
                repository.save(admin);
            }
        };
    }
}
