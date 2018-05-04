package br.com.accenture.projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().matches("123", "$2a$10$lQvk3DnF0TflMmo78EPuOOYGJajzTzW7ufOpaUNM8.Qez3duvMdRm"));
//	}
	
}
