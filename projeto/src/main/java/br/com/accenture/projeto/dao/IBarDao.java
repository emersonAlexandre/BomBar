package br.com.accenture.projeto.dao;

import java.util.List;

import br.com.accenture.projeto.exceptions.CnpjJaExistenteException;
import br.com.accenture.projeto.exceptions.DiaDaSemanaInvalidoException;
import br.com.accenture.projeto.model.Bar;

/**
 * Interface que contém a declaração de todos os métodos relacionados a manipulação de objetos do tipo Bar, os quais as classes concretas devem implementar 
 */
public interface IBarDao {

	/**
	 * Método responsável por salvar no banco de dados um objeto do tipo Bar que é passado por parâmetro 
	 * @param bar (Objeto que será salvo no banco de dados, caso esteja válido)
	 * @throws CnpjJaExistenteException (Exceção que retorna uma mensagem informando que já existe um Bar cadastrado com o mesmo CNPJ)
	 */
	void salvar(Bar bar) throws CnpjJaExistenteException, DiaDaSemanaInvalidoException;

	/**
	 * Método responsável por atualizar no banco de dados as informações de um objeto do tipo Bar que é passado por parâmetro 
	 * @param bar (Objeto que será atualizado no banco de dados, caso esteja válido)
	 * @throws CnpjJaExistenteException (Exceção que retorna uma mensagem informando que já existe um Bar cadastrado com o mesmo CNPJ)
	 */
	void atualizar(Bar bar) throws CnpjJaExistenteException, DiaDaSemanaInvalidoException;

	/**
	 * Método responsável por excluir um objeto do tipo Bar no banco de dados, com base no id que é passado por parâmetro
	 * @param id (Id do objeto que será deletado)
	 */
	void deletar(Long id);

	/**
	 * Método responsável por trazer um objeto do tipo Bar do banco de dados, com base no id que é passado por parâmetro
	 * @param id (Id do objeto que será buscado no banco de dados)
	 * @return (Se o objeto for encontrado, o mesmo é retornado retorna, caso contrário será retornado null)
	 */
	Bar buscarPoId(Long id);

	/**
	 * Método responsável por trazer todos os objetos do tipo Bar do banco de dados
	 * @return (Se for encontrado algum registro no banco de dados, será retornado uma lista de objetos do tipo Bar, caso contrário será retornado null)
	 */
	List<Bar> buscarTodos();

	List<Bar> buscarBar(Bar bar);

}
