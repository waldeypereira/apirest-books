package com.company.books.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigSecurity {

    @Value("${app.security.user1.username}")
    private String user1Username;

    @Value("${app.security.user1.password}")
    private String user1Password;

    @Value("${app.security.user2.username}")
    private String user2Username;

    @Value("${app.security.user2.password}")
    private String user2Password;

    @Value("${app.security.user3.username}")
    private String user3Username;

    @Value("${app.security.user3.password}")
    private String user3Password;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails fran = User.builder()
                .username(user1Username)
                .password(passwordEncoder.encode(user1Password))
                .roles("Empleado")
                .build();

        UserDetails agustin = User.builder()
                .username(user2Username)
                .password(passwordEncoder.encode(user2Password))
                .roles("Empleado", "Jefe")
                .build();

        UserDetails edita = User.builder()
                .username(user3Username)
                .password(passwordEncoder.encode(user3Password))
                .roles("Empleado", "Admin", "Editor")
                .build();

        return new InMemoryUserDetailsManager(fran, agustin, edita);
    }
}
