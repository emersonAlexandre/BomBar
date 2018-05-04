package br.com.accenture.projeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.accenture.projeto.dao.impl.UsuarioDao;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDao userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/bares/*").hasAnyRole("PG_BARES")
				.antMatchers("/").hasAnyRole("PG_BUSCA_BARES")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/entrar")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/entrar?logout")
				.permitAll()
			.and()
			.rememberMe()
				.userDetailsService(userDetailsService);
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.
//		authorizeRequests()
//		.antMatchers("/").hasAnyRole("PG_BUSCA_BARES")
//		.antMatchers("/bares/**").hasAnyRole("PG_BARES")
//		.anyRequest()
//		.authenticated()
//		.antMatchers("/cadastroUsuario").permitAll()
//		.and()
//		.formLogin()
//		.loginPage("/entrar")
//		.permitAll()
//		.and()
//		.logout()
//		.logoutSuccessUrl("/entrar?logout")
//		.permitAll()
//		.and()
//		.rememberMe()
//		.userDetailsService(userDetailsService);
//		.and()
//		.exceptionHandling().accessDeniedPage("/403");
//	}

}
