package com.me2.config;

import com.me2.global.enums.EnumUserRole;
import com.me2.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private final JwtFilter jwtFilter;

    @Autowired
    private final AuthenticationConfiguration configuration;

    public SecurityConfiguration(JwtFilter jwtFilter, AuthenticationConfiguration configuration) {
        this.jwtFilter = jwtFilter;
        this.configuration = configuration;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->
                        auth.
                        // public
                        // customer - authentication
                        requestMatchers(HttpMethod.POST, "/api/v0/customer/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v0/customer/authenticate").permitAll()
                        .requestMatchers("/api/v0/customer/test").hasAuthority(EnumUserRole.USER.name())
                        // admin - authenticaton
                        .requestMatchers(HttpMethod.POST,"/api/v0/admin/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v0/admin/authenticate").permitAll()
                        .requestMatchers("/api/v0/admin/test").hasAuthority(EnumUserRole.ADMIN.name())

                        // admin
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/user/**").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "api/v0/admin/user/**").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "api/v0/admin/user/**").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/users/**").hasAuthority(EnumUserRole.ADMIN.name())

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }
}