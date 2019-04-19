package br.com.wscontazul.model.presenter;

import br.com.wscontazul.model.Ca07Meta;

public class ListaMeta extends Ca07Meta {
	
	private String status;
	private boolean isPodeAplicar;
	private double valorRestante;
	
	public ListaMeta(String status, Double valorRestante, Ca07Meta meta) {
		
		super(meta.getDescricao(), meta.getValor(), meta.getIsAvista(),  meta.getValorEconomizar(), 
				meta.getNumeroContazul(), meta.getQuantidadeParcela(), meta.getIsRealizada());
		super.setId(meta.getId());
		this.status = status;
		this.valorRestante = valorRestante;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isPodeAplicar() {
		return isPodeAplicar;
	}

	public void setPodeAplicar(boolean isPodeAplicar) {
		this.isPodeAplicar = isPodeAplicar;
	}

	public double getValorRestante() {
		return valorRestante;
	}

	public void setValorRestante(double valorRestante) {
		this.valorRestante = valorRestante;
	}	
}
