package br.com.wscontazul.model.presenter;

import br.com.wscontazul.model.Ca06DividaMensal;

public class ListaContasAPagar extends Ca06DividaMensal{

	private String strUltimaDataPagamento;

	public ListaContasAPagar(Ca06DividaMensal dividaMensal, String strUltimaDataPagamento) {
		
		super(dividaMensal.getDescricao(), dividaMensal.getValor(), dividaMensal.getPrioridade(), 
				dividaMensal.getQuantidadePaga(), dividaMensal.getQuantidadeParcela(), 
				dividaMensal.getPago(), dividaMensal.getDataPagamento(), dividaMensal.getNumeroContazul());
		super.setId(dividaMensal.getId());
		this.strUltimaDataPagamento = strUltimaDataPagamento;
	}

	public String getStrUltimaDataPagamento() {
		return strUltimaDataPagamento;
	}

	public void setStrUltimaDataPagamento(String strUltimaDataPagamento) {
		this.strUltimaDataPagamento = strUltimaDataPagamento;
	}
}