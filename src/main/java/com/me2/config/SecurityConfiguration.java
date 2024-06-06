package com.me2.config;

import com.me2.exception.entry_point.CustomAccessDeniedEntryPoint;
import com.me2.exception.entry_point.CustomBasicAuthenticationEntryPoint;
import com.me2.global.enums.EnumUserRole;
import com.me2.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    private final AuthenticationConfiguration configuration;

    private final CustomBasicAuthenticationEntryPoint authenticationEntryPoint;

    private final CustomAccessDeniedEntryPoint accessDeniedEntryPoint;

    public SecurityConfiguration(JwtFilter jwtFilter,
                                 AuthenticationConfiguration configuration,
                                 CustomBasicAuthenticationEntryPoint authenticationEntryPoint, CustomAccessDeniedEntryPoint accessDeniedEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.configuration = configuration;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedEntryPoint = accessDeniedEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->
                        auth.
                        // public
                        requestMatchers(HttpMethod.POST, "/api/v0/media/public/upload/image").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v0/public/promotion/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v0/public/product/list").permitAll()
                        // customer - authentication
                        .requestMatchers(HttpMethod.POST, "/api/v0/customer/register").permitAll()
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
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/user/list").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/users").hasAuthority(EnumUserRole.ADMIN.name())

                        // admin - category
                        .requestMatchers(HttpMethod.POST, "api/v0/admin/category").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "api/v0/admin/category").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/category/**").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/category").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/category/list").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "api/v0/admin/category").hasAuthority(EnumUserRole.ADMIN.name())
                        // admin - promotion
                        .requestMatchers(HttpMethod.POST, "api/v0/admin/promotion").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "api/v0/admin/promotion").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/promotion/**").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/promotion").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/promotion/list").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "api/v0/admin/promotion").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "api/v0/admin/promotion/approve").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "api/v0/admin/promotion/approve/**").hasAuthority(EnumUserRole.ADMIN.name())
                        // admin - product
                        .requestMatchers(HttpMethod.POST, "api/v0/admin/product").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "api/v0/admin/product").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/product/**").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/product").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "api/v0/admin/product/list").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "api/v0/admin/product").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "api/v0/admin/product/active").hasAuthority(EnumUserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "api/v0/admin/product/approve").hasAuthority(EnumUserRole.ADMIN.name())

                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(HttpBasicConfigurer::disable)
                .exceptionHandling(e -> e
                        .accessDeniedHandler(accessDeniedEntryPoint)
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
        ;
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