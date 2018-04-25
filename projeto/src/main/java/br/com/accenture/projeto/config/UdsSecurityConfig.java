package br.com.accenture.projeto.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.accenture.projeto.dao.impl.UsuarioDao;

@Configuration
public class UdsSecurityConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder, 
			PasswordEncoder passwordEncoder, 
			UsuarioDao userDetailsService) throws Exception {
		builder
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
}
