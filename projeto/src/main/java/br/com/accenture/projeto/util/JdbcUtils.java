package br.com.accenture.projeto.util;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.accenture.projeto.model.Usuario;

public class JdbcUtils {
	
	public static final long PG_BUSCA_BARES = 1;
	public static final long PG_BARES = 2;
	
	public static final String USUARIO_POR_LOGIN = "SELECT * FROM usuario"
			+ " WHERE login = '";
	
	public static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.nome as nome_permissao FROM usuario_permissao up"
			+ " JOIN usuario u ON u.id = up.usuario_id"
			+ " JOIN permissao p ON p.id = up.permissao_id"
			+ " WHERE u.login = '";
	
	public static void atribuirPermissao(Usuario usuario, long permissao_id, JdbcTemplate jdbcTemplate) {

		jdbcTemplate.update(
				"INSERT INTO usuario_permissao (usuario_id, permissao_id) VALUES (?, ?)",
				usuario.getId(), permissao_id);
	}

}
