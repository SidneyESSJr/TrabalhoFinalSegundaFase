package br.com.grupoVoid.util;

/**
 *
 * @author maryucha
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConversorData {

    /**
     * java.util.date to java.sql.date
     */
    public static void conversorDeData() {
        java.util.Date uDate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");
        System.out.println("Using a dateFormat date is : " + df.format(uDate));
    }

    public static java.sql.Date converterUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
