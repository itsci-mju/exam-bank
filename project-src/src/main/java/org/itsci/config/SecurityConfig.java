package org.itsci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(configurer -> {
            configurer.antMatchers("/home/**").authenticated()
                    .antMatchers("/system/**").hasRole("ADMIN")
                    .antMatchers("/member/**").hasRole("MEMBER")
                    .antMatchers("/teacher/**").hasRole("TEACHER");
        });

        http.exceptionHandling(configurer -> {
            configurer.accessDeniedPage("/access-denied");
        });

        http.formLogin(configurer -> {
                    try {
                        configurer.loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .permitAll()
                                .and()
                                .logout().permitAll();
                    } catch (Exception e) { e.printStackTrace(); }
                }
        );
        return http.build();
    }
}
