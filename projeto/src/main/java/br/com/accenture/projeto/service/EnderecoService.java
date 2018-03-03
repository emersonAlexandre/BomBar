package br.com.accenture.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accenture.projeto.dao.IEnderecoDao;
import br.com.accenture.projeto.model.Endereco;

@Service
public class EnderecoService {

	@Autowired
	IEnderecoDao enderecoDao;

	public List<Endereco> buscarTodos() {

		return enderecoDao.buscarTodos();

	}

	public void salvar(Endereco endereco) {
		enderecoDao.salvar(endereco);
	}

	public void editar(Endereco endereco) {
		enderecoDao.atualizar(endereco);
	}

	public void excluir(Long id) {
		enderecoDao.deletar(id);
	}

	public Endereco buscarPorId(Long id) {
		return enderecoDao.buscarPoId(id);
	}

}