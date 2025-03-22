package com.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)//disables csrf - enabled by default - use if not using session based auth(JWT , Basic Auth, API tokens)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/home/**", "/article/**", "/css/**").permitAll()
                        .requestMatchers( "/favicon.ico").permitAll()
                        .requestMatchers("/edit/**","/admin/**","/new/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // test with/without property
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
