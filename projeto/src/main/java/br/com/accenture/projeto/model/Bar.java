package br.com.accenture.projeto.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.accenture.projeto.enums.DiasDaSemana;
import br.com.accenture.projeto.enums.TiposDeComida;
import br.com.accenture.projeto.enums.TiposDeMusica;

public class Bar {

	private Long id;

	@NotBlank
	private String nome;

	@CNPJ
	@NotBlank
	private String cnpj;

	@NotBlank
	private String contato;

	@Email
	private String email;

	@NotNull
	private DiasDaSemana diaInicial;

	@NotNull
	private DiasDaSemana diaFinal;

	@NotBlank
	private String horarioInicial;

	@NotBlank
	private String horarioFinal;

	@NotNull
	private TiposDeMusica tipoDeMusica;

	@NotNull
	private TiposDeComida tipoDeComida;

	private boolean temEstacionamento;

	private boolean estacionamentoPago;

	private boolean temCover;

	private boolean coverPago;
	
	@NotNull
	@Valid
	private Endereco endereco;
	
	public void construtorComum(String nome, String cnpj, String contato, DiasDaSemana diaInicial,
			DiasDaSemana diaFinal, String horarioInicial, String horarioFinal, TiposDeMusica tipoDeMusica,
			TiposDeComida tipoDeComida, boolean temEstacionamento, boolean estacionamentoPago, boolean temCover,
			boolean coverPago, Endereco endereco) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.contato = contato;
		this.diaInicial = diaInicial;
		this.diaFinal = diaFinal;
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
		this.tipoDeMusica = tipoDeMusica;
		this.tipoDeComida = tipoDeComida;
		this.temEstacionamento = temEstacionamento;
		this.estacionamentoPago = estacionamentoPago;
		this.temCover = temCover;
		this.coverPago = coverPago;
		this.endereco = endereco;
	}
	
	public Bar(Long id, String nome, String cnpj, String contato, String email, DiasDaSemana diaInicial,
			DiasDaSemana diaFinal, String horarioInicial, String horarioFinal, TiposDeMusica tipoDeMusica,
			TiposDeComida tipoDeComida, boolean temEstacionamento, boolean estacionamentoPago, boolean temCover,
			boolean coverPago, Endereco endereco) {
		construtorComum(nome, cnpj, contato, diaInicial, diaFinal, horarioInicial, horarioFinal, tipoDeMusica, tipoDeComida, temEstacionamento, estacionamentoPago, temCover, coverPago, endereco);
		this.id = id;
		this.email = email;
	}
	
	public Bar(String nome, String cnpj, String contato, DiasDaSemana diaInicial,
			DiasDaSemana diaFinal, String horarioInicial, String horarioFinal, TiposDeMusica tipoDeMusica,
			TiposDeComida tipoDeComida, boolean temEstacionamento, boolean estacionamentoPago, boolean temCover,
			boolean coverPago, Endereco endereco) {
		construtorComum(nome, cnpj, contato, diaInicial, diaFinal, horarioInicial, horarioFinal, tipoDeMusica, tipoDeComida, temEstacionamento, estacionamentoPago, temCover, coverPago, endereco);
	}

	public Bar() {
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DiasDaSemana getDiaInicial() {
		return diaInicial;
	}

	public void setDiaInicial(DiasDaSemana diaInicial) {
		this.diaInicial = diaInicial;
	}

	public DiasDaSemana getDiaFinal() {
		return diaFinal;
	}

	public void setDiaFinal(DiasDaSemana diaFinal) {
		this.diaFinal = diaFinal;
	}

	public String getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public String getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public TiposDeMusica getTipoDeMusica() {
		return tipoDeMusica;
	}

	public void setTipoDeMusica(TiposDeMusica tipoDeMusica) {
		this.tipoDeMusica = tipoDeMusica;
	}

	public TiposDeComida getTipoDeComida() {
		return tipoDeComida;
	}

	public void setTipoDeComida(TiposDeComida tipoDeComida) {
		this.tipoDeComida = tipoDeComida;
	}

	public boolean isTemEstacionamento() {
		return temEstacionamento;
	}

	public void setTemEstacionamento(boolean temEstacionamento) {
		this.temEstacionamento = temEstacionamento;
	}

	public boolean isEstacionamentoPago() {
		return estacionamentoPago;
	}

	public void setEstacionamentoPago(boolean estacionamentoPago) {
		this.estacionamentoPago = estacionamentoPago;
	}

	public boolean isTemCover() {
		return temCover;
	}

	public void setTemCover(boolean temCover) {
		this.temCover = temCover;
	}

	public boolean isCoverPago() {
		return coverPago;
	}

	public void setCoverPago(boolean coverPago) {
		this.coverPago = coverPago;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Bar [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", contato=" + contato + ", email=" + email
				+ ", diaInicial=" + diaInicial + ", diaFinal=" + diaFinal + ", horarioInicial=" + horarioInicial
				+ ", horarioFinal=" + horarioFinal + ", tipoDeMusica=" + tipoDeMusica + ", tipoDeComida=" + tipoDeComida
				+ ", temEstacionamento=" + temEstacionamento + ", estacionamentoPago=" + estacionamentoPago
				+ ", temCover=" + temCover + ", coverPago=" + coverPago + ", endereco=" + endereco + "]";
	}

	public boolean isNovo() {
		return id == null;
	}

}
