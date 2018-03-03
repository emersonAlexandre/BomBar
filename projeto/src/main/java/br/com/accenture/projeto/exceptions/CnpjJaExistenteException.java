package br.com.accenture.projeto.exceptions;

public class CnpjJaExistenteException extends Exception{

	private static final long serialVersionUID = 1L;

	public CnpjJaExistenteException(String message) {
		super(message);
	}
	
}
