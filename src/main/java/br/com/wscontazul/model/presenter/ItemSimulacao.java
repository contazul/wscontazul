package br.com.wscontazul.model.presenter;

public class ItemSimulacao {
	
	private boolean temDivida;
	private boolean temBeneficio;
	private boolean temMeta;
	
	public ItemSimulacao() {
		
		this.temDivida = false;
		this.temBeneficio = false;
		this.temMeta = false;
	}
	
	public ItemSimulacao(boolean temDivida, boolean temBeneficio, boolean temMeta) {
		
		this.temDivida = temDivida;
		this.temBeneficio = temBeneficio;
		this.temMeta = temMeta;
	}

	public boolean isTemDivida() {
		return temDivida;
	}

	public void setTemDivida(boolean temDivida) {
		this.temDivida = temDivida;
	}

	public boolean isTemBeneficio() {
		return temBeneficio;
	}

	public void setTemBeneficio(boolean temBeneficio) {
		this.temBeneficio = temBeneficio;
	}

	public boolean isTemMeta() {
		return temMeta;
	}

	public void setTemMeta(boolean temMeta) {
		this.temMeta = temMeta;
	}
}
