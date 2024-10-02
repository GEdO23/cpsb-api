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
                                "/formulario_cadastrar_pet",
                                "/formulario_atualizar_pet/{id}",
                                "/remover_pet/{id}",
                                "/formulario_cadastrar_raca")
                        .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .formLogin((form) -> form.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?erro_login=true")
                        .permitAll())
                .logout((logout) -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
