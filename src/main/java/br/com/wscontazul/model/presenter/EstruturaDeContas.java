package br.com.wscontazul.model.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.wscontazul.model.Ca02Contazul;

public class EstruturaDeContas {
	
	private List<Ca02Contazul> comuns;
	private List<Centralizacao> centralizacoes;
	private boolean podeCriarNovaEstrutura;
	
	public EstruturaDeContas() {
		
		this.comuns = new ArrayList<>();
		this.centralizacoes = new ArrayList<>();
		this.podeCriarNovaEstrutura = false;
	}

	public EstruturaDeContas(List<Ca02Contazul> comuns, List<Centralizacao> centralizacoes,
			boolean podeCriarNovaEstrutura) {
		
		this.comuns = comuns;
		this.centralizacoes = centralizacoes;
		this.podeCriarNovaEstrutura = podeCriarNovaEstrutura;
	}

	public List<Ca02Contazul> getComuns() {
		return comuns;
	}

	public void setComuns(List<Ca02Contazul> comuns) {
		this.comuns = comuns;
	}

	public List<Centralizacao> getCentralizacoes() {
		return centralizacoes;
	}

	public void setCentralizacoes(List<Centralizacao> centralizacoes) {
		this.centralizacoes = centralizacoes;
	}

	public boolean isPodeCriarNovaEstrutura() {
		return podeCriarNovaEstrutura;
	}

	public void setPodeCriarNovaEstrutura(boolean podeCriarNovaEstrutura) {
		this.podeCriarNovaEstrutura = podeCriarNovaEstrutura;
	}
}
