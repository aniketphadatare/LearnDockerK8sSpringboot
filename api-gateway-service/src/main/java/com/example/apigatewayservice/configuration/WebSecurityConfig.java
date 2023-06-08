package com.example.apigatewayservice.configuration;

import com.example.apigatewayservice.jwtconverter.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    public static final String CUSTOMER = "customer";
    public static final String SELLER = "seller";
    private final JwtAuthConverter jwtAuthConverter;
    public WebSecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/login", "/login/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/homepage", "/homepage/**").hasAnyRole(CUSTOMER, SELLER)
                .requestMatchers(HttpMethod.GET, "/ValidCustomer", "/ValidCustomer/**").hasRole(CUSTOMER)
                .requestMatchers(HttpMethod.GET, "/v1/createProduct", "/v1/createProduct/**").hasRole(SELLER)
                .anyRequest().authenticated());

        http.oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }
}
