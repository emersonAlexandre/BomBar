package br.com.accenture.projeto.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Endereco {

	private Long id;
	
	@NotBlank
	private String logradouro;
	
	@NotNull
	private Integer numero;
	
	private String complemento;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String estado;
	
	private String cep;
	
	public void construtorComum(String logradouro, Integer numero, String bairro, String cidade,
			String estado) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public Endereco(Long id, String logradouro, Integer numero, String complemento, String bairro, String cidade,
			String estado, String cep) {
		construtorComum(logradouro, numero, bairro, cidade, estado);
		this.id = id;
		this.complemento = complemento;
		this.cep = cep;
	}
	
	public Endereco(String logradouro, Integer numero, String bairro, String cidade,
			String estado) {
		construtorComum(logradouro, numero, bairro, cidade, estado);
	}
	
	public Endereco() {
		
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return logradouro + ", " + numero + ", " + bairro + ", " + cidade + " - " + estado;
	}
	
}