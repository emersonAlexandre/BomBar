package br.com.accenture.projeto.enums;

public enum TiposDeMusica {
	SERTANEJA(1, "Sertaneja"),
	FORRO(2, "Forró"),
	ROCK(3, "Rock"),
	POP(4, "Pop"),
	PAGODE(5, "Pagode/Samba"),
	MPB(6, "MPB"),
	BREGA(7, "Brega");

	private final String descricao;

	private final Integer codigo;

	TiposDeMusica(Integer codigo, String descricao) {
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
