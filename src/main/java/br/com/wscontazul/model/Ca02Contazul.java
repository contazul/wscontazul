package br.com.wscontazul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ca_02_contazul")
@Entity(name = "Ca02Contazul")
public class Ca02Contazul {
	
	@Id
	@Column(name = "numero_contazul")
	private long numeroContazul;
	
	@Column(name = "saldo")
	private double saldo;
	
	@Column(name = "valor_ideal")
	private double valorIdeal;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "perfil")
	private String perfil;
	
	@Column(name = "id_usuario")
	private long idUsuario;
	
	public Ca02Contazul() {
		
	}
	
	public Ca02Contazul(String perfil, long numeroContazul) {
		
		this.perfil = perfil;
		this.numeroContazul = numeroContazul;
	}
	
	public Ca02Contazul(long numeroContazul, double saldo, double valorIdeal, String descricao,
			String perfil, long idUsuario) {
		
		this.numeroContazul = numeroContazul;
		this.saldo = saldo;
		this.valorIdeal = valorIdeal;
		this.descricao = descricao;
		this.perfil = perfil;
		this.idUsuario = idUsuario;
	}

	public long getNumeroContazul() {
		return numeroContazul;
	}

	public void setNumeroContazul(long numeroContazul) {
		this.numeroContazul = numeroContazul;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getValorIdeal() {
		return valorIdeal;
	}

	public void setValorIdeal(double valorIdeal) {
		this.valorIdeal = valorIdeal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
}













