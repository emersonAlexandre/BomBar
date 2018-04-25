package br.com.accenture.projeto.dao.impl;

import static br.com.accenture.projeto.util.JdbcUtils.PERMISSOES_POR_USUARIO;
import static br.com.accenture.projeto.util.JdbcUtils.USUARIO_POR_LOGIN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.accenture.projeto.model.Usuario;

@Component
public class UsuarioDao implements UserDetailsService{

	private Logger logger = Logger.getLogger(UsuarioDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario userDetails = new Usuario();

		try {
			userDetails = buscarUsuario(login);
			
			Collection<GrantedAuthority> permissoesPorUsuario = buscarPermissoes(login, PERMISSOES_POR_USUARIO);

			userDetails.getAuthorities().addAll(permissoesPorUsuario);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userDetails;

	}

	public Usuario buscarUsuario(String login) throws SQLException {
		try {
			List<Usuario> usuarios = jdbcTemplate.query(USUARIO_POR_LOGIN+login+"';", new RowMapper<Usuario>() {

				@Override
				public Usuario mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return new Usuario(rs.getString("nome"), login, rs.getString("senha"), rs.getBoolean("ativo"));
				}

			});

			if(!usuarios.isEmpty()) {
				return usuarios.get(0);
			}

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um bar em buscarporId()", e);

		}

		return null;
	}

	public Collection<GrantedAuthority> buscarPermissoes(String login, String sql) throws SQLException {
		try {
			List<GrantedAuthority> usuarios = jdbcTemplate.query(sql+login+"';", new RowMapper<GrantedAuthority>() {

				@Override
				public GrantedAuthority mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return new SimpleGrantedAuthority(rs.getString("nome_permissao"));
				}

			});

			if(!usuarios.isEmpty()) {
				return usuarios;
			}

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um bar em buscarporId()", e);

		}

		return null;
	}

}
