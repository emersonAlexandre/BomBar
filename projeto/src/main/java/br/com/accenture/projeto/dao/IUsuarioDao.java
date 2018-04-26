package br.com.accenture.projeto.dao;

import java.util.List;

import br.com.accenture.projeto.model.Usuario;

/**
 * Interface que contém a declaração de todos os métodos relacionados a manipulação de objetos do tipo Usuario, os quais as classes concretas devem implementar 
 */
public interface IUsuarioDao {

	/**
	 * Método responsável por salvar no banco de dados um objeto do tipo Usuario que é passado por parâmetro 
	 * @param usuario (Objeto que será salvo no banco de dados, caso esteja válido)
	 */
	void salvar(Usuario usuario);

	/**
	 * Método responsável por atualizar no banco de dados as informações de um objeto do tipo Usuário que é passado por parâmetro 
	 * @param usuario (Objeto que será atualizado no banco de dados, caso esteja válido)
	 */
	void atualizar(Usuario usuario);

	/**
	 * Método responsável por excluir um objeto do tipo Usuario no banco de dados, com base no id que é passado por parâmetro
	 * @param id (Id do objeto que será deletado)
	 */
	void deletar(Long id);

	/**
	 * Método responsável por trazer um objeto do tipo Usuario do banco de dados, com base no id que é passado por parâmetro
	 * @param id (Id do objeto que será buscado no banco de dados)
	 * @return (Se o objeto for encontrado, o mesmo é retornado retorna, caso contrário será retornado null)
	 */
	Usuario buscarPoId(Long id);

	/**
	 * Método responsável por trazer todos os objetos do tipo Usuario do banco de dados
	 * @return (Se for encontrado algum registro no banco de dados, será retornado uma lista de objetos do tipo Usuario, caso contrário será retornado null)
	 */
	List<Usuario> buscarTodos();

}
