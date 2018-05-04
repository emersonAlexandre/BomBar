package br.com.accenture.projeto.model;


import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.accenture.projeto.enums.Grupo;

public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String login;
	
	@NotBlank
	private String senha;
	
	private boolean ativo;
	
	@NotNull
	private Grupo grupo;
	
	private Collection<GrantedAuthority> permissoes = new ArrayList<>();

	public void construtorComum(String nome, String login, 
			String senha, boolean ativo) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
	}

	public Usuario(Long id, String nome, String login, 
			String senha, boolean ativo) {
		this.id = id;
		construtorComum(nome, login, senha, ativo);
	}

	public Usuario(String nome, String login, 
			String senha, boolean ativo) {
		construtorComum(nome, login, senha, ativo);
	}
	
	public Usuario() {
		
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return ativo;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", ativo=" + ativo
				+ ", grupo=" + grupo + ", permissoes=" + permissoes + "]";
	}

}