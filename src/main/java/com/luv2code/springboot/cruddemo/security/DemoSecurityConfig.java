package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC...no more hardcoded values
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_id, pw, active FROM members WHERE user_id=?");

        // define query to retrieve user by Authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT user_id, role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                configurer.
                        requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // Use Http Basic Authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery(CSRF)
        // In general, not required for Stateless REST APIs that use POST, GET, DELETE and/or PATCH
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }


     /* Not needed - This comes from DB
    @Bean
 public InMemoryUserDetailsManager userDetailsManager() {

     UserDetails marcus = User.builder()
             .username("Marcus")
             .password("{noop}test123")
             .roles("BOSS", "EMPLOYEE")
             .build();

     UserDetails sophie = User.builder()
             .username("Sophie")
             .password("{noop}test123")
             .roles("WIFE_OF_BOSS", "EMPLOYEE")
             .build();

     UserDetails ilona = User.builder()
             .username("Ilona")
             .password("{noop}test123")
             .roles("DAUGHTER, DAUGHTER_OF_BOSS")
             .build();

     return new InMemoryUserDetailsManager(marcus, sophie, ilona);
 }
*/
}
