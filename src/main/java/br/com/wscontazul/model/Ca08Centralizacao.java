package br.com.wscontazul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ca_08_centralizacao")
@Entity(name = "Ca08Centralizacao")
public class Ca08Centralizacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_centralizacao")
	private long id;
	
	@Column(name = "numero_contazul_centralizadora")
	private long numeroContazulCentralizadora;
	
	@Column(name = "numero_contazul_centralizada")
	private long numeroContazulCentralizada;
	
	public Ca08Centralizacao() {
		
		this.id = 0;
		this.numeroContazulCentralizadora = 0;
		this.numeroContazulCentralizada = 0;
	}

	public Ca08Centralizacao(long id, long numeroContazulCentralizadora, long numeroContazulCentralizada) {
	
		this.id = id;
		this.numeroContazulCentralizadora = numeroContazulCentralizadora;
		this.numeroContazulCentralizada = numeroContazulCentralizada;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumeroContazulCentralizadora() {
		return numeroContazulCentralizadora;
	}

	public void setNumeroContazulCentralizadora(long numeroContazulCentralizadora) {
		this.numeroContazulCentralizadora = numeroContazulCentralizadora;
	}

	public long getNumeroContazulCentralizada() {
		return numeroContazulCentralizada;
	}

	public void setNumeroContazulCentralizada(long numeroContazulCentralizada) {
		this.numeroContazulCentralizada = numeroContazulCentralizada;
	}
}
