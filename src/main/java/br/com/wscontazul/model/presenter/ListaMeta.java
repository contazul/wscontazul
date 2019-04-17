package br.com.wscontazul.model.presenter;

import br.com.wscontazul.model.Ca07Meta;

public class ListaMeta extends Ca07Meta {
	
	private double valorRestante;
	private String status;
	
	public ListaMeta(double valorRestante, String status, Ca07Meta meta) {
		
		super(meta.getDescricao(), meta.getValor(), 0,  meta.getValorEconomizar(), 
				0L, 0);
		super.setDataAplicacao(meta.getDataAplicacao());
		super.setQuantidadePaga(meta.getQuantidadePaga());
		super.setId(meta.getId());
		this.valorRestante = (valorRestante < 0)? 0 : valorRestante;   
		this.status = status;
	}

	public double getValorRestante() {
		return valorRestante;
	}

	public void setValorRestante(double valorRestante) {
		this.valorRestante = valorRestante;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
