package br.com.accenture.projeto.enums;

public enum TiposDeComida {
	REGIONAL(1, "Regional"),
	GOURMET(2, "Gourmet"),
	CHURRASCO(3, "Churrasco"),
	ITALIANA(4, "Italiana"),
	ORIENTAL(5, "Oriental"),
	PETISCOS(6, "Petiscos"),
	CALDINHO(7, "Caldinho"),
	FRUTOS_DO_MAR(8, "Frutos do mar");

	private final String descricao;

	private final Integer codigo;

	TiposDeComida(Integer codigo, String descricao) {
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
