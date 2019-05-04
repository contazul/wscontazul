package br.com.wscontazul.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.wscontazul.statics.Contazul;

public class UtilContazul {
	
	public long gerardorDeContazul() {

        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date dateTime = Calendar.getInstance().getTime();
        String strTime = sdf.format(dateTime);
        return this.getFormatoContazul(day, month, year, strTime);
    }

    private long getFormatoContazul(int day, int month, int year, String strTime) {

        String strDay = this.inserirZeroAEsquerda("" + day);
        String strMonth = this.inserirZeroAEsquerda("" + month);
        String strYear = this.inserirZeroAEsquerda("" + year);
        String formatAccount = "DDMMYYYY";
        formatAccount = formatAccount.replace("DD", strDay);
        formatAccount = formatAccount.replace("MM", strMonth);
        formatAccount = formatAccount.replace("YYYY", strYear);
        strTime = strTime.replaceAll(":", "");
        formatAccount += strTime;
        
        return Long.parseLong(formatAccount);
    }

    private String inserirZeroAEsquerda(String str) {

        String zero = "0";
        if(str.length() == 1) {

            str = zero + str;
        }
        return str;
    }

    public String gerarStatusContazul(double valorIdeal, double totalDividaMensal, double totalBeneficioMensal) {
    	
    	if(valorIdeal == 0) {
    		
    		return Contazul.STATUS5;
    	}
    	
    	double economiaAtual = totalBeneficioMensal - totalDividaMensal;
    	
    	double percentualSobreValorIdeal = (economiaAtual * 100) / valorIdeal;
    	
    	
    	if(percentualSobreValorIdeal < 10) {
    		
    		return Contazul.STATUS4;
    	}
    	
    	if(percentualSobreValorIdeal == 11 || percentualSobreValorIdeal <= 50) {
    		
    		return Contazul.STATUS3;
    	}
    	
    	if(percentualSobreValorIdeal == 51 || percentualSobreValorIdeal <= 99) {
    		
    		return Contazul.STATUS2;
    	} else {
    		
    		return Contazul.STATUS1;
    	}
    }
    
    public double calcularPercentualEconomizado(double totalBeneficioMensal, double totalDividaMensal) {
    	
    	if(totalDividaMensal == 0) 
    		return 100;
    	
    	if(totalBeneficioMensal - totalDividaMensal <= 0)
    		return 0;
    	
    	if(totalBeneficioMensal == 0)
    		return 0;
    	
    	double percentual = (totalDividaMensal * 100) / totalBeneficioMensal;
    	
    	if(percentual >= 100)
    		return 100;
    	
    	return 100 - percentual;
    }
    
    public double calcularValorEconomizado(double totalBeneficioMensal, double totalDividaMensal) {
    	
    	return totalBeneficioMensal - totalDividaMensal;
    }
}
























