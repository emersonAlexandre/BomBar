package br.com.accenture.projeto.enums;

public enum Grupo {
	ADMIN(1, "Administrador"),
	CLIENTE(2, "Cliente"),
	FUNCIONARIO(3, "Funcionário");

	private final String descricao;

	private final Integer codigo;

	Grupo(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}
}
