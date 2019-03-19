package br.com.wscontazul.model.presenter;

public class ListaLucroMensal {
	
	private long id;
	private String descricao;
	private double valor;
	private String ultimaDataRecebimento;
	
	public ListaLucroMensal() {
		
	}

	public ListaLucroMensal(long id, String descricao, double valor, String ultimaDataRecebimento) {
		
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.ultimaDataRecebimento = ultimaDataRecebimento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getUltimaDataRecebimento() {
		return ultimaDataRecebimento;
	}

	public void setUltimaDataRecebimento(String ultimaDataRecebimento) {
		this.ultimaDataRecebimento = ultimaDataRecebimento;
	}
}
