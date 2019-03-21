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

    public void setSaldo(Double saldo) {
    	
    	if(saldo == null) {
    		
    		this.saldo = 0;
    	} else {
    		
    		this.saldo = saldo;
    	}
    }

    public double getTotalSubtracaoSaldoBaixaPrioridade() {
        return totalSubtracaoSaldoBaixaPrioridade;
    }

    public void setTotalSubtracaoSaldoBaixaPrioridade(Double totalSubtracaoSaldoBaixaPrioridade) {
    	
    	if(totalSubtracaoSaldoBaixaPrioridade == null) {
    		
    		this.totalSubtracaoSaldoBaixaPrioridade = 0;
    	} else {
    		
    		this.totalSubtracaoSaldoBaixaPrioridade = totalSubtracaoSaldoBaixaPrioridade;
    	}
    }

    public double getTotalSubtracaoSaldoGeral() {
        return totalSubtracaoSaldoGeral;
    }

    public void setTotalSubtracaoSaldoGeral(Double totalSubtracaoSaldoGeral) {
    	
    	if(totalSubtracaoSaldoGeral == null) {
    		
    		this.totalSubtracaoSaldoGeral = 0;
    	} else {
    		
    		this.totalSubtracaoSaldoGeral = totalSubtracaoSaldoGeral;
    	}
    }

    public double getTotalBeneficioMensal() {
        return totalBeneficioMensal;
    }

    public void setTotalBeneficioMensal(Double totalBeneficioMensal) {
    	
    	if(totalBeneficioMensal == null) {
    		
    		this.totalBeneficioMensal = 0;
    	} else {
    		
    		this.totalBeneficioMensal = totalBeneficioMensal;
    	}
    }

    public double getTotalSomaSaldo() {
        return totalSomaSaldo;
    }

    public void setTotalSomaSaldo(Double totalSomaSaldo) {
    	
    	if(totalSomaSaldo == null) {
    		
    		this.totalSomaSaldo = 0;
    	} else {
    		
    		this.totalSomaSaldo = totalSomaSaldo;
    	}
    }
}















