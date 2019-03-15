package br.com.wscontazul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ca_03_soma_saldo")
@Entity(name = "Ca03SomaSaldo")
public class Ca03SomaSaldo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private long id;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "numero_contazul")
	private long numeroContazul;
	
	public Ca03SomaSaldo() {
		
	}

	public Ca03SomaSaldo(double valor, String descricao, long numeroContazul) {
		
		this.valor = valor;
		this.descricao = descricao;
		this.numeroContazul = numeroContazul;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getNumeroContazul() {
		return numeroContazul;
	}

	public void setNumeroContazul(long numeroContazul) {
		this.numeroContazul = numeroContazul;
	}
}
