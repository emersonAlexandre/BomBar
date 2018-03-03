package br.com.accenture.projeto.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.accenture.projeto.dao.IBarDao;
import br.com.accenture.projeto.enums.DiasDaSemana;
import br.com.accenture.projeto.enums.TiposDeComida;
import br.com.accenture.projeto.enums.TiposDeMusica;
import br.com.accenture.projeto.exceptions.CnpjJaExistenteException;
import br.com.accenture.projeto.exceptions.DiaDaSemanaInvalidoException;
import br.com.accenture.projeto.model.Bar;
import br.com.accenture.projeto.service.EnderecoService;

/**
 * Classe responsável por manipular os dados de um objeto do tipo Bar em um banco de dados
 */
@Repository
public class BarDaoImpl implements IBarDao {

	/**
	 * Injetando JdbcTemplate(Implementação do Spring que utiliza o Jdbc do Java) para uso nos métodos da classe
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Injetando EnderecoService para utilizar os métodos CRUD relacionados a endereço
	 */
	@Autowired
	private EnderecoService endService;

	/**
	 * Instância da classe Logger utilizada para facilitar o debug do código, caso ocorra algum erro
	 */
	private Logger logger = Logger.getLogger(BarDaoImpl.class);


	/**
	 * Método responsável por verificar se já existe algum registro no banco de dados com o mesmo CNPJ do objeto que está sendo passado por parâmetro
	 * @param bar (Objeto do tipo Bar que está sendo validado)
	 * @throws CnpjJaExistenteException (Exceção que retorna uma mensagem informando que já existe um Bar cadastrado com o mesmo CNPJ) 
	 * @throws ParseException 
	 */
	public void verificaCnpj(Bar bar) throws CnpjJaExistenteException , DiaDaSemanaInvalidoException{

		for (Bar b : buscarTodos()) {
			if(b.getId() != bar.getId()) {
				if (b.getCnpj().equals(bar.getCnpj())) {
					throw new CnpjJaExistenteException("Já existe bar cadastrado com este CNPJ");
				}
			}
		}

		if (bar.getDiaInicial() == bar.getDiaFinal()) {
			throw new DiaDaSemanaInvalidoException("Não pode cadastrar um bar com os dias de funcionamento iguais");
		}

	}

	@Override
	public void salvar(Bar bar) throws CnpjJaExistenteException, DiaDaSemanaInvalidoException{

		verificaCnpj(bar);

		jdbcTemplate.update(
				"INSERT INTO bar (nome, cnpj, contato, email, dia_inicial, dia_final, horario_inicial, horario_final, tipo_musica, tipo_comida, tem_estacionamento, estacionamento_pago, tem_cover, cover_pago, id_endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				bar.getNome(), bar.getCnpj(), bar.getContato(), bar.getEmail(), bar.getDiaInicial().toString(), bar.getDiaFinal().toString(), bar.getHorarioInicial(), bar.getHorarioFinal(), bar.getTipoDeMusica().toString(), bar.getTipoDeComida().toString(), bar.isTemEstacionamento(), bar.isEstacionamentoPago(), bar.isTemCover(), bar.isCoverPago(), bar.getEndereco().getId());
	}

	@Override
	public void atualizar(Bar bar) throws CnpjJaExistenteException, DiaDaSemanaInvalidoException{

		verificaCnpj(bar);

		jdbcTemplate.update("UPDATE bar SET nome = ?, cnpj = ?, contato = ?, email = ?, dia_inicial = ?, dia_final = ?, horario_inicial = ?, horario_final = ?, tipo_musica = ?, tipo_comida = ?, "
				+ "tem_estacionamento = ?, estacionamento_pago = ?, tem_cover = ?, cover_pago = ?, id_endereco = ? WHERE id = ?", bar.getNome(), bar.getCnpj(), bar.getContato(), bar.getEmail(), 
				bar.getDiaInicial().toString(), bar.getDiaFinal().toString(), bar.getHorarioInicial(), bar.getHorarioFinal(), bar.getTipoDeMusica().toString(), bar.getTipoDeComida().toString(), 
				bar.isTemEstacionamento(), bar.isEstacionamentoPago(), bar.isTemCover(), bar.isCoverPago(), bar.getEndereco().getId(), bar.getId());
	}

	@Override
	public void deletar(Long id) {
		jdbcTemplate.update("DELETE FROM bar WHERE id = ?", id);
	}

	/**
	 * Método responsável por criar e retornar um objeto do tipo Bar, a partir de um ResultSet que é passado por parâmetro
	 */
	public Bar montarObjeto(ResultSet rs) throws SQLException {
		return new Bar(rs.getLong("id"), rs.getString("nome"), rs.getString("cnpj"), rs.getString("contato"), rs.getString("email"), DiasDaSemana.valueOf(rs.getString("dia_inicial")), 
				DiasDaSemana.valueOf(rs.getString("dia_final")), rs.getString("horario_inicial"), rs.getString("horario_final"), TiposDeMusica.valueOf(rs.getString("tipo_musica")), 
				TiposDeComida.valueOf(rs.getString("tipo_comida")), rs.getBoolean("tem_estacionamento"), rs.getBoolean("estacionamento_pago") , rs.getBoolean("tem_cover"), rs.getBoolean("cover_pago"),
				endService.buscarPorId(rs.getLong("id_endereco")));
	}

	@Override
	public Bar buscarPoId(Long id) {
		try {
			List<Bar> bares = jdbcTemplate.query("SELECT * FROM bar WHERE id = "+id, new RowMapper<Bar>() {

				@Override
				public Bar mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return montarObjeto(rs);
				}

			});

			if(!bares.isEmpty()) {
				return bares.get(0);
			}

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um bar em buscarporId()", e);

		}

		return null;
	}

	@Override
	public List<Bar> buscarTodos() {

		try {
			List<Bar> bares = jdbcTemplate.query("SELECT * FROM bar", new RowMapper<Bar>() {

				@Override
				public Bar mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return montarObjeto(rs);
				}

			});

			return bares;

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um bar em buscarTodos()", e);

		}

		return null;
	}

	@Override
	public List<Bar> buscarBar(Bar bar) {
		try {
			List<Bar> bares = jdbcTemplate.query("SELECT * FROM bar WHERE tipo_musica = '"+bar.getTipoDeMusica().toString()+"' AND tipo_comida = '"+bar.getTipoDeComida().toString()+
					"' AND tem_cover = "+bar.isTemCover()+" AND cover_pago = "+bar.isCoverPago()+" AND tem_estacionamento = "+bar.isTemEstacionamento()+" AND estacionamento_pago = "+bar.isEstacionamentoPago(), new RowMapper<Bar>() {

				@Override
				public Bar mapRow(ResultSet rs, int rowNumb) throws SQLException {
					return montarObjeto(rs);
				}

			});

			return bares;

		} catch (DataAccessException e) {
			logger.error("Erro ao buscar um bar em buscarporId()", e);

		}

		return null;
	}

}
