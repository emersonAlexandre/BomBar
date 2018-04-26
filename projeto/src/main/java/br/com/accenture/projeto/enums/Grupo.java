package br.com.accenture.projeto.enums;

public enum Grupo {
	ADMIN("Adimistrador"),
	FUNCIONARIO("Funcionário"),
	CLIENTE("Cliente");
	
	private String tipoDeUsuario;
	
	private Grupo(String tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public String getTipoDeUsuario() {
		return tipoDeUsuario;
	}
}