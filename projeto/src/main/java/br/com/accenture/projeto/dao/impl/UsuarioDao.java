package br.com.accenture.projeto.dao.impl;

import static br.com.accenture.projeto.util.JdbcUtils.PERMISSOES_POR_USUARIO;
import static br.com.accenture.projeto.util.JdbcUtils.USUARIO_POR_LOGIN;
import static br.com.accenture.projeto.util.JdbcUtils.atribuirPermissao;
import static br.com.accenture.projeto.util.JdbcUtils.PG_BARES;
import static br.com.accenture.projeto.util.JdbcUtils.PG_BUSCA_BARES;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.accenture.projeto.dao.IUsuarioDao;
import br.com.accenture.projeto.enums.Grupo;
import br.com.accenture.projeto.model.Usuario;

@Component
public class UsuarioDao implements UserDetailsService, IUsuarioDao{

	private Logger logger = Logger.getLogger(UsuarioDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void salvar(Usuario usuario) {
		
		jdbcTemplate.update(
				"INSERT INTO usuario (nome, login, senha, ativo) VALUES (?, ?, ?, ?)",
				usuario.getNome(), usuario.getUsername(), new BCryptPasswordEncoder().encode(usuario.getPassword()), usuario.isEnabled());
		
		Usuario user_saved = new Usuario();
		
		try {
			user_saved = buscarUsuario(usuario.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(usuario.getGrupo().equals(Grupo.ADMIN)) {
			atribuirPermissao(user_saved, PG_BUSCA_BARES, jdbcTemplate);
			atribuirPermissao(user_saved, PG_BARES, jdbcTemplate);
		}
		
		else {
			atribuirPermissao(user_saved, PG_BUSCA_BARES, jdbcTemplate);
		}
	}

	@Override
	public void atualizar(Usuario usuario) {

		jdbcTemplate.update("UPDATE usuario SET nome = ?, login = ?, senha = ?, ativo = ? WHERE id = ?", usuario.getNome(), 
				usuario.getUsername(), usuario.getPassword(), usuario.isEnabled());
	}

	@Override
	public void deletar(Long id) {
		jdbcTemplate.update("DELETE FROM bar WHERE id = ?", id);		
	}

	/**
	 * Método responsável por criar e retornar um objeto do tipo Usuario, a partir de um ResultSet que é passado por parâmetro
	 */
	public Usuario montarObjeto(ResultSet rs) throws SQLException {
		return new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getBoolean("ativo"));
	}

	@Override
	public Usuario buscarPoId(Long id) {
		try {
			List<Usuario> usuarios = jdbcTemplate.query("SELECT * FROM usuario WHERE id = "+id, new RowMapper<Usuario>() {

				@Override
				public Usuario mapRow(ResultSet rs, int rowNumb) throws SQLException {
					Usuario user = montarObjeto(rs);
					user.getAuthorities().addAll(buscarPermissoes(user.getUsername(), PERMISSOES_POR_USUARIO));
					return user;
				}

			});

			if(!usuarios.isEmpty()) {
				return usuarios.get(0);
			}

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um usuario em buscarporId()", e);

		}

		return null;
	}

	@Override
	public List<Usuario> buscarTodos() {

		try {
			List<Usuario> usuarios = jdbcTemplate.query("SELECT * FROM usuario", new RowMapper<Usuario>() {

				@Override
				public Usuario mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return montarObjeto(rs);
				}

			});

			return usuarios;

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um usuario em buscarTodos()", e);

		}

		return null;
	}

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
					return montarObjeto(rs);
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
