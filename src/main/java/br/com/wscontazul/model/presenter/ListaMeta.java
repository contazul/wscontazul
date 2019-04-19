package br.com.wscontazul.model.presenter;

import br.com.wscontazul.model.Ca07Meta;
import br.com.wscontazul.util.UtilDatas;

public class ListaMeta extends Ca07Meta {
	
	private double valorRestante;
	private String status;
	private boolean isPagoMesCorrente;
	private String strDataAplicacao;
	
	public ListaMeta(double valorRestante, String status, boolean isPagoMesCorrente,Ca07Meta meta) {
		
		super(meta.getDescricao(), meta.getValor(), 0,  meta.getValorEconomizar(), 
				0L, 0, 0);
		super.setDataAplicacao(meta.getDataAplicacao());
		super.setQuantidadePaga(meta.getQuantidadePaga());
		super.setId(meta.getId());
		this.valorRestante = (valorRestante < 0)? 0 : valorRestante;   
		this.status = status;
		this.isPagoMesCorrente = isPagoMesCorrente;
		UtilDatas utilDatas = new UtilDatas();
		this.strDataAplicacao = utilDatas.converterSqlDateParaString(meta.getDataAplicacao());
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

	public boolean isPagoMesCorrente() {
		return isPagoMesCorrente;
	}

	public void setPagoMesCorrente(boolean isPagoMesCorrente) {
		this.isPagoMesCorrente = isPagoMesCorrente;
	}

	public String getStrDataAplicacao() {
		return strDataAplicacao;
	}

	public void setStrDataAplicacao(String strDataAplicacao) {
		this.strDataAplicacao = strDataAplicacao;
	}
}
