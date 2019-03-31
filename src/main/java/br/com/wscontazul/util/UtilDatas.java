package br.com.wscontazul.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UtilDatas {

    public Date getDataCorrente() {

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        return date;
    }

    private String getMesAtual() {

        GregorianCalendar calendar = new GregorianCalendar();
        String mes = "" + (calendar.get(GregorianCalendar.MONTH) + 1);
        if(mes.length() == 1) {

            return "0" + mes;
        }

        return mes;
    }

    private String getAnoAtual() {

        GregorianCalendar calendar = new GregorianCalendar();
        return "" + calendar.get(GregorianCalendar.YEAR);
    }

    private boolean validarData(String data, String formato) {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException ex) {

            return false;
        }
    }

    private String[] getStringIntervaloDeDatasMensal() {

        String mes = getMesAtual();
        String[] intervalo = new String[2];
        switch (mes) {

            case "01":

                intervalo[0] = "01/01/" + getAnoAtual();
                intervalo[1] = "31/01/" + getAnoAtual();
                break;
            case "02":

                intervalo[0] = "01/02/" + getAnoAtual();
                if(validarData("29/02/" + getAnoAtual(), "dd/MM/yyyy")) {
                    intervalo[1] = "29/02/" + getAnoAtual();
                } else {

                    intervalo[1] = "28/02/" + getAnoAtual();
                }
                break;
            case "03":

                intervalo[0] = "01/03/" + getAnoAtual();
                intervalo[1] = "31/03/" + getAnoAtual();
                break;
            case "04":

                intervalo[0] = "01/04/" + getAnoAtual();
                intervalo[1] = "30/04/" + getAnoAtual();
                break;
            case "05":

                intervalo[0] = "01/05/" + getAnoAtual();
                intervalo[1] = "31/05/" + getAnoAtual();
                break;
            case "06":

                intervalo[0] = "01/06/" + getAnoAtual();
                intervalo[1] = "30/06/" + getAnoAtual();
                break;
            case "07":

                intervalo[0] = "01/07/" + getAnoAtual();
                intervalo[1] = "31/07/" + getAnoAtual();
                break;
            case "08":

                intervalo[0] = "01/08/" + getAnoAtual();
                intervalo[1] = "31/08/" + getAnoAtual();
                break;
            case "09":

                intervalo[0] = "01/09/" + getAnoAtual();
                intervalo[1] = "31/09/" + getAnoAtual();
                break;
            case "10":

                intervalo[0] = "01/10/" + getAnoAtual();
                intervalo[1] = "31/10/" + getAnoAtual();
                break;
            case "11":

                intervalo[0] = "01/11/" + getAnoAtual();
                intervalo[1] = "30/11/" + getAnoAtual();
                break;
            case "12":

                intervalo[0] = "01/12/" + getAnoAtual();
                intervalo[1] = "31/12/" + getAnoAtual();
                break;
        }

        return intervalo;
    }

    public Date[] getDateIntervaloDeDatasMensal() {

        Date[] dateIntervalo = new Date[2];
        String[] intervalo = getStringIntervaloDeDatasMensal();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {

            java.sql.Date data01 = new java.sql.Date(format.parse(intervalo[0]).getTime());
            dateIntervalo[0] = data01;
            java.sql.Date data02 = new java.sql.Date(format.parse(intervalo[1]).getTime());
            dateIntervalo[1] = data02;
        } catch (ParseException pe) {

            pe.printStackTrace();
        }

        return dateIntervalo;
    }

    public Date converterStringEmSqlDate(String strData) {

        DateFormat format = new SimpleDateFormat("dd/MM");

        try {

            return new java.sql.Date(format.parse(strData).getTime());
        } catch (ParseException pe) {

            pe.printStackTrace();
        }

        return null;
    }
    
    public String converterSqlDateParaString(Date data) {
    	
    	if(data != null) {
    		
    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    		return df.format(data);
    	}
    	
    	return null;
    }
}


















