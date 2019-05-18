package br.com.wscontazul.model.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.wscontazul.model.Ca02Contazul;

public class Centralizacao {
	
	private Ca02Contazul centralizadora;
	private List<Ca02Contazul> centralizadas;
	
	public Centralizacao() {
		
		this.centralizadora = new Ca02Contazul();
		this.centralizadas = new ArrayList<>();
	}
	
	public Centralizacao(Ca02Contazul centralizadora, List<Ca02Contazul> centralizadas) {
		
		this.centralizadora = centralizadora;
		this.centralizadas = centralizadas;
	}

	public Ca02Contazul getCentralizadora() {
		return centralizadora;
	}

	public void setCentralizadora(Ca02Contazul centralizadora) {
		this.centralizadora = centralizadora;
	}

	public List<Ca02Contazul> getCentralizadas() {
		return centralizadas;
	}

	public void setCentralizadas(List<Ca02Contazul> centralizadas) {
		this.centralizadas = centralizadas;
	}
}
