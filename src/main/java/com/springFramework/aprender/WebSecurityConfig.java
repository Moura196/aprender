package com.springFramework.aprender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
					.requestMatchers("/").permitAll()
					.requestMatchers("/login").permitAll()
					.requestMatchers("/users").hasAnyRole("USERS", "ADMINS")
					.requestMatchers("/admins").hasAnyRole("ADMINS")
					.anyRequest().authenticated()
			);
			
		return http.build();
	}
		
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
			.username("user")
			.password("{noop}teste")
			.roles("USERS")
			.build();
		UserDetails admin = User.builder()
			.username("admin")
			.password("{noop}teste12")
			.roles("ADMINS")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

}