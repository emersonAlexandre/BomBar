package br.com.accenture.projeto.util;

public class JdbcUtils {
	
	public static final String USUARIO_POR_LOGIN = "SELECT * FROM usuario"
			+ " WHERE login = '";
	
	public static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.nome as nome_permissao FROM usuario_permissao up"
			+ " JOIN usuario u ON u.id = up.usuario_id"
			+ " JOIN permissao p ON p.id = up.permissao_id"
			+ " WHERE u.login = '";

}
