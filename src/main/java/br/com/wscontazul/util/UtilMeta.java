package br.com.wscontazul.util;

import br.com.wscontazul.statics.Meta;

// 12032019195445
public class UtilMeta {
	
	public String calcularStatus(double totalBeneficioMensal, double totalDividas, 
			double valorMeta, double valorEconomizar, int isAvista) {
		
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
		return valorEconomizar - valorBase;
	}
}
