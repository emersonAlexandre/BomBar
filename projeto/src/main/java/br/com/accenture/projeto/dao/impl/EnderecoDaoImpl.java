package br.com.accenture.projeto.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.accenture.projeto.dao.IEnderecoDao;
import br.com.accenture.projeto.model.Endereco;

/**
 * Classe responsável por manipular os dados de um objeto do tipo Endereço em um banco de dados
 */
@Repository
public class EnderecoDaoImpl implements IEnderecoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Logger logger = Logger.getLogger(EnderecoDaoImpl.class);

	@Override
	public void salvar(Endereco endereco){
		jdbcTemplate.update(
				"INSERT INTO endereco (logradouro, numero, complemento, bairro, cidade, estado, cep) values (?, ?, ?, ?, ?, ?, ?)",
				endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
	}

	@Override
	public void atualizar(Endereco endereco) {
		jdbcTemplate.update("UPDATE endereco SET logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?",
				endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), endereco.getId());
	}

	@Override
	public void deletar(Long id) {
		jdbcTemplate.update("DELETE FROM endereco WHERE id = ?", id);
	}

	/**
	 * Método responsável por criar e retornar um objeto do tipo Endereço, a partir de um ResultSet que é passado por parâmetro
	 */
	public Endereco montarObjeto(ResultSet rs) throws SQLException {

		return new Endereco(rs.getLong("id"), rs.getString("logradouro"), rs.getInt("numero"),rs.getString("complemento") , rs.getString("bairro") , rs.getString("cidade") , rs.getString("estado"), rs.getString("cep") );
	}

	@Override
	public Endereco buscarPoId(Long id) {
		
		try {
			List<Endereco> enderecos = jdbcTemplate.query("SELECT * FROM endereco WHERE id = "+id, new RowMapper<Endereco>() {

				@Override
				public Endereco mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return montarObjeto(rs);
				}

			});

			if (!enderecos.isEmpty()) {
				return enderecos.get(0);
			}

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um endereco em buscarporId()", e);

		}

		return null;
	}

	@Override
	public List<Endereco> buscarTodos() {
		try {
			List<Endereco> enderecos = jdbcTemplate.query("SELECT * FROM endereco", new RowMapper<Endereco>() {

				@Override
				public Endereco mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return montarObjeto(rs);
				}

			});

			return enderecos;

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um endereco em buscarTodos()", e);

		}

		return null;
	}

}