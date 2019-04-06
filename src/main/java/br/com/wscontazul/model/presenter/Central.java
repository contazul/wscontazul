package br.com.wscontazul.model.presenter;

public class Central {

    private String status;
    private double saldo;
    private double totalSubtracaoSaldoBaixaPrioridade;
    private double totalSubtracaoSaldoGeral;
    private double totalBeneficioMensal;
    private double totalSomaSaldo;
    private double percentualEconomizado;
    private double totalDividaMensal;
    private double totalDividaMensalBaixaPrioridade;

    public Central() {

    }

	public Central(String status, double saldo, double totalSubtracaoSaldoBaixaPrioridade,
			double totalSubtracaoSaldoGeral, double totalBeneficioMensal, double totalSomaSaldo,
			double percentualEconomizado, double totalDividaMensal, double totalDividaMensalBaixaPrioridade) {
		super();
		this.status = status;
		this.saldo = saldo;
		this.totalSubtracaoSaldoBaixaPrioridade = totalSubtracaoSaldoBaixaPrioridade;
		this.totalSubtracaoSaldoGeral = totalSubtracaoSaldoGeral;
		this.totalBeneficioMensal = totalBeneficioMensal;
		this.totalSomaSaldo = totalSomaSaldo;
		this.percentualEconomizado = percentualEconomizado;
		this.totalDividaMensal = totalDividaMensal;
		this.totalDividaMensalBaixaPrioridade = totalDividaMensalBaixaPrioridade;
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

	public void setTotalSubtracaoSaldoBaixaPrioridade(Double totalSubtracaoSaldoBaixaPrioridade) {
		
		if(totalSubtracaoSaldoBaixaPrioridade == null)
			this.totalSubtracaoSaldoBaixaPrioridade = 0;
		else
		this.totalSubtracaoSaldoBaixaPrioridade = totalSubtracaoSaldoBaixaPrioridade;
	}

	public double getTotalSubtracaoSaldoGeral() {
		return totalSubtracaoSaldoGeral;
	}

	public void setTotalSubtracaoSaldoGeral(Double totalSubtracaoSaldoGeral) {
		
		if(totalSubtracaoSaldoGeral == null)
			this.totalSubtracaoSaldoGeral = 0;
		else
		this.totalSubtracaoSaldoGeral = totalSubtracaoSaldoGeral;
	}

	public double getTotalBeneficioMensal() {
		return totalBeneficioMensal;
	}

	public void setTotalBeneficioMensal(Double totalBeneficioMensal) {
		
		if(totalBeneficioMensal == null)
			this.totalBeneficioMensal = 0;
		else
		this.totalBeneficioMensal = totalBeneficioMensal;
	}

	public double getTotalSomaSaldo() {
		return totalSomaSaldo;
	}

	public void setTotalSomaSaldo(Double totalSomaSaldo) {
		
		if(totalSomaSaldo == null)
			this.totalSomaSaldo = 0;
		else
		this.totalSomaSaldo = totalSomaSaldo;
	}

	public double getPercentualEconomizado() {
		return percentualEconomizado;
	}

	public void setPercentualEconomizado(double percentualEconomizado) {
		this.percentualEconomizado = percentualEconomizado;
	}

	public double getTotalDividaMensal() {
		return totalDividaMensal;
	}

	public void setTotalDividaMensal(Double totalDividaMensal) {
		
		if(totalDividaMensal == null)
			this.totalDividaMensal = 0;
		else
		this.totalDividaMensal = totalDividaMensal;
	}

	public double getTotalDividaMensalBaixaPrioridade() {
		return totalDividaMensalBaixaPrioridade;
	}

	public void setTotalDividaMensalBaixaPrioridade(Double totalDividaMensalBaixaPrioridade) {
		
		if(totalDividaMensalBaixaPrioridade == null)
			this.totalDividaMensalBaixaPrioridade = 0;
		else
		this.totalDividaMensalBaixaPrioridade = totalDividaMensalBaixaPrioridade;
	}
}















