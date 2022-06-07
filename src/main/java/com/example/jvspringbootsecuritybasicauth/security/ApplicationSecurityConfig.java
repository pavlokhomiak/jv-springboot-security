package com.example.jvspringbootsecuritybasicauth.security;

import static com.example.jvspringbootsecuritybasicauth.security.ApplicationUserRole.*;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*").permitAll()
            .antMatchers("/students/**").hasRole(ApplicationUserRole.STUDENT.name())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails bobUser = User.builder()
            .username("bob")
            .password(passwordEncoder.encode("pas"))
            .authorities(STUDENT.getGrantedAuthorities())
            .build();

        UserDetails alisaUser = User.builder()
            .username("alisa")
            .password(passwordEncoder.encode("pas123"))
            .authorities(ADMIN.getGrantedAuthorities())
            .build();

        UserDetails pavloUser = User.builder()
            .username("pavlo")
            .password(passwordEncoder.encode("pas123"))
            .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
            .build();

        return new InMemoryUserDetailsManager(bobUser, alisaUser, pavloUser);
    }
}
