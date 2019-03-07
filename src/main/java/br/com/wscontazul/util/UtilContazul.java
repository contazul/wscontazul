package br.com.wscontazul.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
}







