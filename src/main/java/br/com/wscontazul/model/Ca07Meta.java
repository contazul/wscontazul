package br.com.wscontazul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ca_07_meta")
@Entity(name = "Ca07Meta")
public class Ca07Meta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_meta")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "is_avista")
	private Integer isAvista;
	
	@Column(name = "valor_economizar")
	private Double valorEconomizar;
	
	@Column(name = "numero_contazul")
	private Long numeroContazul;
	
	@Column(name = "quantidade_parcela")
	private Integer quantidadeParcela;
	
	@Column(name = "is_realizada")
	private Integer isRealizada;
	
	public Ca07Meta() {
		
	}

	public Ca07Meta(String descricao, Double valor, Integer isAvista, Double valorEconomizar, Long numeroContazul,
			Integer quantidadeParcela, Integer isRealizada) {
		
		this.descricao = descricao;
		this.valor = valor;
		this.isAvista = isAvista;
		this.valorEconomizar = valorEconomizar;
		this.numeroContazul = numeroContazul;
		this.quantidadeParcela = quantidadeParcela;
		this.isRealizada = isRealizada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getIsAvista() {
		return isAvista;
	}

	public void setIsAvista(Integer isAvista) {
		this.isAvista = isAvista;
	}

	public Double getValorEconomizar() {
		return valorEconomizar;
	}

	public void setValorEconomizar(Double valorEconomizar) {
		this.valorEconomizar = valorEconomizar;
	}

	public Long getNumeroContazul() {
		return numeroContazul;
	}

	public void setNumeroContazul(Long numeroContazul) {
		this.numeroContazul = numeroContazul;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public Integer getIsRealizada() {
		return isRealizada;
	}

	public void setIsRealizada(Integer isRealizada) {
		this.isRealizada = isRealizada;
	}
}
