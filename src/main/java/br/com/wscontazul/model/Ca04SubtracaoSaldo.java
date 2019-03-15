package br.com.wscontazul.model;

import javax.persistence.*;

@Table(name = "ca_04_subtracao_saldo")
@Entity(name = "Ca04SubtracaoSaldoRepository")
public class Ca04SubtracaoSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subtracao_saldo")
    private long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private double valor;

    @Column(name = "prioridade")
    private String prioridade;

    @Column(name = "numero_contazul")
    private long numeroContazul;

    public Ca04SubtracaoSaldo() {

    }

    public Ca04SubtracaoSaldo(String descricao, double valor, String prioridade, long numeroContazul) {

        this.descricao = descricao;
        this.valor = valor;
        this.prioridade = prioridade;
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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public long getNumeroContazul() {
        return numeroContazul;
    }

    public void setNumeroContazul(long numeroContazul) {
        this.numeroContazul = numeroContazul;
    }
}
