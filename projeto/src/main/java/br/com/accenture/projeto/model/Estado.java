package br.com.accenture.projeto.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Estado implements Serializable {

	private static final long serialVersionUID = -37984257645544778L;

	@SerializedName("ID")
	private String id;

	@SerializedName("Sigla")
	private String sigla;

	@SerializedName("Nome")
	private String nome;

	public Estado(String id) {
		this.id = id;
	}
	
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", sigla=" + sigla + ", nome=" + nome + "]";
	}
	
}