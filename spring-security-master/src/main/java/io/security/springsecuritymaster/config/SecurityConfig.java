package io.security.springsecuritymaster.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(configurer -> {
                    configurer
                            .loginPage("/loginPage")
//                            .loginProcessingUrl("/loginProcessing")
                            .defaultSuccessUrl("/home")
                            .failureUrl("/failed")
                            .usernameParameter("userId")
                            .passwordParameter("passwd")
//                            .successHandler((request, response, authentication) -> {
//                                System.out.println("authentification : " + authentication);
//                                response.sendRedirect("/home");
//                            })
//                            .failureHandler((request, response, exception) -> {
//                                System.out.println("exception : " + exception);
//                                response.sendRedirect("/loginPage");
//                            })
                            .permitAll();
                });

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password("{noop}1111")
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("user2")
                .password("{noop}1111")
                .roles("USER")
                .build();

        UserDetails user3 = User.withUsername("user3")
                .password("{noop}1111")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}