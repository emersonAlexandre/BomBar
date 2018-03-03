package br.com.accenture.projeto.enums;

public enum DiasDaSemana {
	SEGUNDA(1, "Segunda-feira"),
	TERCA(2, "Terça-feira"),
	QUARTA(3, "Quarta-feira"),
	QUINTA(4, "Quinta-feira"),
	SEXTA(5, "Sexta-feira"),
	SABADO(6, "Sábado"),
	DOMINGO(7, "Domingo");

	private final String descricao;

	private final Integer codigo;

	DiasDaSemana(Integer codigo, String descricao) {
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
