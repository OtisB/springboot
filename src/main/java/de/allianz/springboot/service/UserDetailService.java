package de.allianz.springboot.service;

import de.allianz.springboot.config.PasswordEncoderConfig;
import de.allianz.springboot.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class UserDetailService {

    private  final PasswordEncoder passwordEncoder;
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .authorities(Role.MEMBER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin-password")
                .authorities(Role.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password("{noop}analyst-password")
                .authorities(Role.ANALYST.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user, admin, analyst);
    }
}
