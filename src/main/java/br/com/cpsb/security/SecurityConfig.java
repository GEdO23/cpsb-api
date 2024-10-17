package br.com.cpsb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests.requestMatchers(
                                "/pets/form-register",
                                "/pets/form-update/{id}",
                                "/pets/remove/{id}",
                                "/breeds/form-register",
                                "/breeds/form-update/{id}",
                                "/breeds/remove/{id}")
                        .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .formLogin((form) -> form.loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?login_error=true")
                        .permitAll())
                .logout((logout) -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll())
                .exceptionHandling((exception) ->
                        exception.accessDeniedHandler(
                                (request, response, accessDeniedException) -> 
                                        response.sendRedirect("/access_denied")
                        ));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
