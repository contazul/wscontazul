package br.com.wscontazul.util;

import br.com.wscontazul.statics.Meta;

// 12032019195445
public class UtilMeta {
	
	public String calcularStatus(double totalBeneficioMensal, double totalDividas, 
			double valorMeta, double valorEconomizar, int isAvista, double saldo) {
		
		if(!verificarPodeAplicar(saldo, valorEconomizar, valorMeta))
			return Meta.STATUS_01;
			
		double valorBase = totalBeneficioMensal - totalDividas;
		
		if(valorBase >= (valorMeta + valorEconomizar) && isAvista == 1) 
			return Meta.STATUS_02;
	
		if(valorBase >= (valorMeta + valorEconomizar))
			return Meta.STATUS_03;
		
		else
			return Meta.STATUS_01;
	}
	
	public double calcularValorRestante(double totalBeneficioMensal, double totalDividas, 
			double valorMeta, double valorEconomizar) {
		
		double valorBase = totalBeneficioMensal - totalDividas;
		double resultado = valorEconomizar - valorBase;
		if(resultado <= 0)
			return 0;
		
		return resultado;
	}
	
	public int verficarTipoMeta(int aVista, int quantidadeParcela) {
		
		// fixa
		if(quantidadeParcela == 0 && aVista == 0)
			return 0;
		
		// A vista
		if(aVista == 1)
			return 1;
		
		// A prazo
		else
			return 2;
		
	}

	public boolean verificarPodeAplicar(Double saldo, Double minimoEconomizar, Double valorMeta) {
		
		// Calcular saldo com base no minimo á economizar após aplicação da meta
		// Verificar se após aplicação da meta o saldo ficará maior ou igual ao minimo a economizar
		if(saldo - valorMeta >= minimoEconomizar)
			return true;
		
		return false;
	}
	
	public boolean verificarNaoPodeSAplicar(String status) {
		
		return !status.equals(Meta.STATUS_01);
	}
}





















