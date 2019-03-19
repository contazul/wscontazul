package br.com.wscontazul.model;

import java.sql.Date;

import javax.persistence.*;

@Table(name = "ca_05_lucro_mensal")
@Entity(name = "Ca05LucroMensal")
public class Ca05LucroMensal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lucro_mensal")
	private long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "valor")
	private double valor;

	@Column(name = "ultima_data_recebimento")
	private Date ultimaDataRecebimento;

	@Column(name = "numero_contazul")
	private long numeroContazul;

	public Ca05LucroMensal() {

	}

	public Ca05LucroMensal(String descricao, double valor, Date ultimaDataRecebimento, long numeroContazul) {
		
		this.descricao = descricao;
		this.valor = valor;
		this.ultimaDataRecebimento = ultimaDataRecebimento;
		this.numeroContazul = numeroContazul;
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

	public Date getUltimaDataRecebimento() {
		return ultimaDataRecebimento;
	}

	public void setUltimaDataRecebimento(Date ultimaDataRecebimento) {
		this.ultimaDataRecebimento = ultimaDataRecebimento;
	}

	public long getNumeroContazul() {
		return numeroContazul;
	}

	public void setNumeroContazul(long numeroContazul) {
		this.numeroContazul = numeroContazul;
	}
}
