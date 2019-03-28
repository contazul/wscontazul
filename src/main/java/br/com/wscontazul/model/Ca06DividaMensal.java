package br.com.wscontazul.model;

import java.sql.Date;

import javax.persistence.*;

@Table(name = "ca_06_divida_mensal")
@Entity(name = "Ca06DividaMensal")
public class Ca06DividaMensal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_divida_mensal")
	private long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "prioridade")
	private String prioridade;
	
	@Column(name = "quantidade_paga")
	private int quantidadePaga;
	
	@Column(name = "quantidade_parcela")
	private int quantidadeParcela;
	
	@Column(name = "pago")
	private int pago;
	
	@Column(name = "data_pagamento")
	private Date dataPagamento;

	public Ca06DividaMensal() {
		
	}

	public Ca06DividaMensal(String descricao, double valor, String prioridade, int quantidadePaga,
			int quantidadeParcela, int pago, Date dataPagamento) {
		this.descricao = descricao;
		this.valor = valor;
		this.prioridade = prioridade;
		this.quantidadePaga = quantidadePaga;
		this.quantidadeParcela = quantidadeParcela;
		this.pago = pago;
		this.dataPagamento = dataPagamento;
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

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public int getQuantidadePaga() {
		return quantidadePaga;
	}

	public void setQuantidadePaga(int quantidadePaga) {
		this.quantidadePaga = quantidadePaga;
	}

	public int getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(int quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public int getPago() {
		return pago;
	}

	public void setPago(int pago) {
		this.pago = pago;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
}
