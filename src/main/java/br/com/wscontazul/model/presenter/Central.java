package br.com.wscontazul.model.presenter;

public class Central {

    private String status;
    private double saldo;
    private double totalSubtracaoSaldoBaixaPrioridade;
    private double totalSubtracaoSaldoGeral;
    private double totalBeneficioMensal;
    private double totalSomaSaldo;

    public Central() {

    }

    public Central(String status, double saldo, double totalSubtracaoSaldoBaixaPrioridade,
                   double totalSubtracaoSaldoGeral, double totalBeneficioMensal, double totalSomaSaldo) {
        this.status = status;
        this.saldo = saldo;
        this.totalSubtracaoSaldoBaixaPrioridade = totalSubtracaoSaldoBaixaPrioridade;
        this.totalSubtracaoSaldoGeral = totalSubtracaoSaldoGeral;
        this.totalBeneficioMensal = totalBeneficioMensal;
        this.totalSomaSaldo = totalSomaSaldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getTotalSubtracaoSaldoBaixaPrioridade() {
        return totalSubtracaoSaldoBaixaPrioridade;
    }

    public void setTotalSubtracaoSaldoBaixaPrioridade(double totalSubtracaoSaldoBaixaPrioridade) {
        this.totalSubtracaoSaldoBaixaPrioridade = totalSubtracaoSaldoBaixaPrioridade;
    }

    public double getTotalSubtracaoSaldoGeral() {
        return totalSubtracaoSaldoGeral;
    }

    public void setTotalSubtracaoSaldoGeral(double totalSubtracaoSaldoGeral) {
        this.totalSubtracaoSaldoGeral = totalSubtracaoSaldoGeral;
    }

    public double getTotalBeneficioMensal() {
        return totalBeneficioMensal;
    }

    public void setTotalBeneficioMensal(double totalBeneficioMensal) {
        this.totalBeneficioMensal = totalBeneficioMensal;
    }

    public double getTotalSomaSaldo() {
        return totalSomaSaldo;
    }

    public void setTotalSomaSaldo(double totalSomaSaldo) {
        this.totalSomaSaldo = totalSomaSaldo;
    }
}
