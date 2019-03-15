package br.com.wscontazul.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wscontazul.util.UtilDatas;

@Table(name = "ca_03_soma_saldo")
@Entity(name = "Ca03SomaSaldo")
public class Ca03SomaSaldo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_soma_saldo")
	private long id;

	@Column(name = "valor")
	private double valor;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "data_movimento")
	private Date dataMovimento;

	@Column(name = "numero_contazul")
	private long numeroContazul;

	public Ca03SomaSaldo() {

	}

	public Ca03SomaSaldo(double valor, String descricao, long numeroContazul) {

		this.valor = valor;
		this.descricao = descricao;
		UtilDatas utilDatas = new UtilDatas();
		this.dataMovimento = utilDatas.getDataCorrente();
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

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public long getNumeroContazul() {
		return numeroContazul;
	}

	public void setNumeroContazul(long numeroContazul) {
		this.numeroContazul = numeroContazul;
	}
}
