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

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/anonymous/**").hasRole("GUEST") //해당 경로에는 익명 권한을 가진 사용자만 접근 허용
                            .requestMatchers("/anonymousContext", "/authenticationCheck").permitAll() // 두 경로에 대해서는 인증 없이 모두 허용
                            .anyRequest().authenticated(); //모든 요청에 대해 인증 요구
                })
                .formLogin(Customizer.withDefaults())
                .anonymous(config -> {
                    config
                            .principal("anon_quest") //익명 사용자들은 anon_quest라는 식별자를 가짐
                            .authorities("ROLE_GUEST"); //익명 사용자들은 GUEST 권한을 가짐
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