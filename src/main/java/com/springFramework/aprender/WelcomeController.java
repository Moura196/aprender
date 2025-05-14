package com.springFramework.aprender;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping
	public String welcome() {
		return "Welcome to my Spring Boot Web API";
	}
	
	@GetMapping("/users")
	public String users() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    System.out.println("Roles do usuário autenticado: " + authorities);
		return "Authorized user";
	}
	
	@GetMapping("/admins")
	public String admins() {
		return "Authorized admin";
	}
	
}