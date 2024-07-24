package org.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.security.authentication.manager.CustomAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(new ObjectMapper(), customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_PATH);

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizedRequests -> authorizedRequests
                                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class);

        return http.build();
    }
}

