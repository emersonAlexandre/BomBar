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
			String senha, boolean ativo, Grupo grupo) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.grupo = grupo;
	}

	public Usuario(Long id, String nome, String login, 
			String senha, boolean ativo, Grupo grupo) {
		this.id = id;
		construtorComum(nome, login, senha, ativo, grupo);
	}

	public Usuario(String nome, String login, 
			String senha, boolean ativo, Grupo grupo) {
		construtorComum(nome, login, senha, ativo, grupo);
	}
	
	public Usuario() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}	

}