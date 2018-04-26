package br.com.accenture.projeto.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.accenture.projeto.dao.impl.UsuarioDao;
import br.com.accenture.projeto.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, SQLException {
		return usuarioDao.loadUserByUsername(login);
	}

	public List<Usuario> buscarTodos() {

		return usuarioDao.buscarTodos();

	}

	public void salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);
	}

	public void editar(Usuario usuario) {
		usuarioDao.atualizar(usuario);
	}

	public void excluir(Long id) {
		usuarioDao.deletar(id);
	}

	public Usuario buscarPorId(Long id) {
		return usuarioDao.buscarPoId(id);
	}

}