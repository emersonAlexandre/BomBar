package br.com.accenture.projeto.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Cidade implements Serializable {

	private static final long serialVersionUID = 4093478932453189L;

	@SerializedName("ID")
	private String id;
	
	@SerializedName("Nome")
	private String nome;
	
	@SerializedName("Estado")
	private String estado;
	
	public Cidade(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", estado=" + estado + "]";
	}

}