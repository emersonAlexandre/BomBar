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
	private String username;
	
	@NotBlank
	private String password;
	
	private boolean enabled;
	
	@NotNull
	private Grupo grupo;
	
	private Collection<GrantedAuthority> authorities = new ArrayList<>();

	public void construtorComum(String nome, String username, 
			String password, boolean enabled, Grupo grupo) {
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.grupo = grupo;
	}

	public Usuario(Long id, String nome, String username, 
			String password, boolean enabled, Grupo grupo) {
		this.id = id;
		construtorComum(nome, username, password, enabled, grupo);
	}

	public Usuario(String nome, String username, 
			String password, boolean enabled, Grupo grupo) {
		construtorComum(nome, username, password, enabled, grupo);
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
		return enabled;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}	

}