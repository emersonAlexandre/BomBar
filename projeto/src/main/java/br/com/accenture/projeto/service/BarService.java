package br.com.accenture.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accenture.projeto.dao.IBarDao;
import br.com.accenture.projeto.exceptions.CnpjJaExistenteException;
import br.com.accenture.projeto.exceptions.DiaDaSemanaInvalidoException;
import br.com.accenture.projeto.model.Bar;

@Service
public class BarService {

	@Autowired
	IBarDao barDao;

	public List<Bar> buscarTodos() {

		return barDao.buscarTodos();

	}

	public void salvar(Bar bar) throws CnpjJaExistenteException, DiaDaSemanaInvalidoException{
		barDao.salvar(bar);
	}

	public void editar(Bar bar) throws CnpjJaExistenteException, DiaDaSemanaInvalidoException{
		barDao.atualizar(bar);
	}

	public void excluir(Long id) {
		barDao.deletar(id);
	}

	public Bar buscarPorId(Long id) {
		return barDao.buscarPoId(id);
	}

	public List<Bar> buscarBar(Bar bar) {
		return barDao.buscarBar(bar);
	}

}