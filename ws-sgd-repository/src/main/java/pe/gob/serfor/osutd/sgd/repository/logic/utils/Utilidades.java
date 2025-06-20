package pe.gob.serfor.osutd.sgd.repository.logic.utils;

public class Utilidades {

	public static String fn_getCleanFileName(String text) {
		if (text != null && text.trim().length() > 0) {
			//text = text.trim().replaceAll("[^-_ABCDEFGHIJKLMN]", "").replaceAll("\\s+", " ");	
			text = text.trim().replaceAll("\\s+", " ");
		}			
		return text;
	}

}
