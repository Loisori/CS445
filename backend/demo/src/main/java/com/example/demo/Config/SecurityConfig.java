package com.example.demo.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    public SecurityConfig() {
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        //Users
                        .requestMatchers(HttpMethod.PUT, "/api/wallet/NapTien").hasAnyRole("CREATOR", "INVESTOR")
                        .requestMatchers(HttpMethod.PUT, "/api/wallet/RutTien").hasAnyRole("CREATOR", "INVESTOR")
                        //Projects
                        .requestMatchers(HttpMethod.DELETE, "/api/Projects/delete/**").hasAnyRole("CREATOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/Projects/update/**").hasRole("CREATOR")
                        .requestMatchers(HttpMethod.POST, "/api/Projects/create/**").hasRole("CREATOR")
                        .requestMatchers(HttpMethod.GET, "/api/Projects/GetAllProjects").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Projects/searchUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Projects/list/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Projects/MyProjects").hasRole("CREATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/Projects/Open/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/Projects/rejected/**").hasRole("ADMIN")
                        //Category
                        .requestMatchers(HttpMethod.POST, "/api/Category/Create/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/Category/Update/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/Category/Delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/Category/ListAll").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Category/list/**").permitAll()
                        //Reward
                        .requestMatchers(HttpMethod.POST, "/api/Reward/Create/**").hasAnyRole("CREATOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/Reward/Update/**").hasAnyRole("CREATOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/Reward/Delete/**").hasAnyRole("CREATOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/Reward/GetAllReward").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/Reward/list/**").permitAll()
                        //Pledge
                        .requestMatchers(HttpMethod.POST, "/api/pledge/Create/**").hasRole("INVESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/pledge/delete/**").hasRole("INVESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/pledge/list/**").hasAnyRole("INVESTOR", "CREATOR")
                        .requestMatchers(HttpMethod.GET, "/api/pledge/showRemaining/**").hasRole("INVESTOR")
                        //Favourites
                        .requestMatchers(HttpMethod.POST, "/api/favourites/create/**").hasRole("INVESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/favourites/list/**").hasRole("INVESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/favourites/delete/**").hasRole("INVESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/favourites/my/**").hasRole("INVESTOR")
                        .requestMatchers("/api/favourites/**").hasRole("ADMIN")
                        //Comments
                        .requestMatchers(HttpMethod.POST, "/api/Comment/Create/**").hasAnyRole("INVESTOR", "CREATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/Comment/Delete/**").hasAnyRole("ADMIN", "CREATOR", "INVESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/Comment/Getall/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/Comment/Update").hasAnyRole("ADMIN", "CREATOR", "INVESTOR")
                        //TransactionHistory
                        .requestMatchers(HttpMethod.GET, "api/TransactionHistory/my").hasAnyRole("CREATOR", "INVESTOR")
                        //Transactiton
                        .requestMatchers(HttpMethod.POST, "/api/Transaction/create/**").hasRole("INVESTOR")
                        .requestMatchers(HttpMethod.PUT, "/api/Transaction/pay/**").hasRole("INVESTOR")
                        //Payout
                        .requestMatchers(HttpMethod.POST, "/api/Payout/Create/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/Payout/Pay/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // Stateless JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
