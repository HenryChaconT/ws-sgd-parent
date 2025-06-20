package pe.gob.serfor.osutd.sgd.repository.logic.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Fecha {
	
	public static String  getFecha(Date  fecha){
		String date ="--/--/---";
		try {
			String pattern = "dd/MM/yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat =   new SimpleDateFormat(pattern);

			 date = simpleDateFormat.format(fecha);
			System.out.println(date);
			
		} 
		catch (Exception e) {
			
		}
		return date;

	}
    public static  int getAnioActual() {
    	Date date = new Date();
    	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	return localDate.getYear();
    }
    public static  String getFechaActual() {
    	String date ="--/--/---";
		try {
			String pattern = "dd/MM/yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat =   new SimpleDateFormat(pattern);
			date = simpleDateFormat.format(new Date());
			System.out.println(date);			
		} 
		catch (Exception e) {
			
		}
		return date;
    }
	
	public static String getStringFechaFormat(String pfecha, String formatoFechaReturn, String formatoPfecha) {
        String vResult = "";
        if (pfecha != null && !pfecha.equals("") && formatoFechaReturn != null && !formatoFechaReturn.equals("") && formatoPfecha != null && 
          !formatoPfecha.equals("")) {
          try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatoPfecha);
            Date date = dateFormat.parse(pfecha);
            dateFormat = new SimpleDateFormat(formatoFechaReturn);
            vResult = dateFormat.format(date);
          } catch (Exception e) {
            e.printStackTrace();
            vResult = "NO_OK";
          } 
        }
        return vResult;
      }
	

}
