package br.com.accenture.projeto.dao;

import java.util.List;

import br.com.accenture.projeto.model.Endereco;

/**
 * Interface que contém a declaração de todos os métodos relacionados a manipulação de objetos do tipo Endereço, os quais as classes concretas devem implementar 
 */
public interface IEnderecoDao {

	/**
	 * Método responsável por salvar no banco de dados um objeto do tipo Endereço que é passado por parâmetro
	 * @param endereco (Objeto que será salvo no banco de dados, caso esteja válido)
	 */
	void salvar(Endereco endereco);

	/**
	 * Método responsável por atualizar no banco de dados as informações de um objeto do tipo Endereco que é passado por parâmetro 
	 * @param endereco (Objeto que será atualizado no banco de dados, caso esteja válido)
	 */
	void atualizar(Endereco endereco);

	/**
	 * Método responsável por excluir um objeto do tipo Endereco no banco de dados, com base no id que é passado por parâmetro
	 * @param id (Id do objeto que será deletado)
	 */
	void deletar(Long id);

	/**
	 * Método responsável por trazer um objeto do tipo Endereco do banco de dados, com base no id que é passado por parâmetro
	 * @param id (Id do objeto que será buscado no banco de dados)
	 * @return (Se o objeto for encontrado, o mesmo é retornado retorna, caso contrário será retornado null)
	 */
	Endereco buscarPoId(Long id);

	/**
	 * Método responsável por trazer todos os objetos do tipo Endereco do banco de dados
	 * @return (Se for encontrado algum registro no banco de dados, será retornado uma lista de objetos do tipo Endereco, caso contrário será retornado null)
	 */
	List<Endereco> buscarTodos();

}