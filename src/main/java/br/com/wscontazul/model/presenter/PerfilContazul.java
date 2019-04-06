package br.com.wscontazul.model.presenter;

import br.com.wscontazul.model.Ca02Contazul;

public class PerfilContazul extends Ca02Contazul {
	
	private String status;
	
	public PerfilContazul(Ca02Contazul ca02Contazul, String status) {
		
		super(ca02Contazul.getPerfil(), ca02Contazul.getNumeroContazul());
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
