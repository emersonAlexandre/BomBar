package br.com.accenture.projeto.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	UserDetailsService usuarioDao;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, SQLException {
		return usuarioDao.loadUserByUsername(login);
	}

}