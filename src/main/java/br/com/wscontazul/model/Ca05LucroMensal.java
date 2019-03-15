package br.com.wscontazul.model;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "ca_05_lucro_mensal")
@Entity(name = "Ca05LucroMensalRepository")
public class Ca05LucroMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lucro_mensal")
    private long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private double valor;

    @Column(name = "dia_recebimento")
    private int diaRecebimento;

    @Column(name = "numero_contazul")
    private long numeroContazul;

    public Ca05LucroMensal() {

    }

    public Ca05LucroMensal(String descricao, double valor, int diaRecebimento, long numeroContazul) {

        this.descricao = descricao;
        this.valor = valor;
        this.diaRecebimento = diaRecebimento;
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

    public int getDiaRecebimento() {
        return diaRecebimento;
    }

    public void setDiaRecebimento(int diaRecebimento) {
        this.diaRecebimento = diaRecebimento;
    }

    public long getNumeroContazul() {
        return numeroContazul;
    }

    public void setNumeroContazul(long numeroContazul) {
        this.numeroContazul = numeroContazul;
    }
}
