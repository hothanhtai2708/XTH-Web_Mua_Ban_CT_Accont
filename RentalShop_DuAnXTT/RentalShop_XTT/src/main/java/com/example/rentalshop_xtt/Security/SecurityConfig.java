package com.example.rentalshop_xtt.Security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();
                })
                .formLogin(formlogin -> formlogin
                        .loginPage("/login")  // Đặt URL trang đăng nhập ở đây
                        .defaultSuccessUrl("/home")
                        .permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .build();
    }
}


