package pe.gob.serfor.osutd.sgd.repository.logic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.request.ServletRequestAttributes;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Documento;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoPass;

//import pe.gob.serfor.wssisged.logic.model.Documento;
//import pe.gob.serfor.wssisged.logic.model.DocumentoPass;

public class Util {
//	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
//			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
//			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	public static boolean esNumerico(String dato) {
		try {
			Integer.parseInt(dato);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public static boolean esNumericoLong(String dato) {
		try {
			Long.parseLong(dato);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public static String obtenerFechaFormateada(Date fechaEntrada) {
		if (fechaEntrada == null) {
			return "";
		}
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			return sf.format(fechaEntrada);
		} catch (Exception e) {
			return "";

		}

	}

	public static String obtenerStringFechaFormat(String pfecha, String formatoFechaReturn, String formatoPfecha) {
		String vResult = "";
		if (pfecha != null && !pfecha.equals("") && formatoFechaReturn != null && !formatoFechaReturn.equals("")
				&& formatoPfecha != null && !formatoPfecha.equals("")) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(formatoPfecha);
				Date date = dateFormat.parse(pfecha);
				dateFormat = new SimpleDateFormat(formatoFechaReturn);
				vResult = dateFormat.format(date);
			} catch (Exception e) {
				e.printStackTrace();
				vResult = "";
			}
		}
		return vResult;
	}

	public static String generateRandomLetter(int length) {
//		String[] _cadena = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
//				"S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] cadena = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String randomString = "";
		try {
			for (int i = 0; i < length; i++) {
				int index = (int) Math.round(Math.random() * 9.0D);
				randomString = randomString + cadena[index];
			}
		} catch (Exception e) {
			randomString = "0";
		}
		return randomString;
	}

	public static boolean esVacio(String dato) {
		if (dato == null) {
			return true;
		}
		dato = dato.trim();
		if (dato.equals("")) {
			return true;
		}
		return false;

	}

	public static String toString(Object obj) {
		if (obj == null) {
			return "";
		}
		try {
			return String.valueOf(obj);
		} catch (Exception e) {
			return "";
		}

	}

	public static String obtenerNombres(Documento documento) {

		if (Util.esVacio(documento.getApeMaterno()) && Util.esVacio(documento.getApePaterno())
				&& Util.esVacio(documento.getNombres())) {
			return "";
		}
		String nombreCompleto = "";
		nombreCompleto = documento.getNombres().concat(" ").concat(documento.getApePaterno()).concat(" ")
				.concat(documento.getApeMaterno());
		return nombreCompleto;

	}

	public static String obtenerNombres(DocumentoPass documento) {

		if (Util.esVacio(documento.getApeMaterno()) && Util.esVacio(documento.getApePaterno())
				&& Util.esVacio(documento.getNombres())) {
			return "";
		}
		String nombreCompleto = "";
		nombreCompleto = documento.getNombres().concat(" ").concat(documento.getApePaterno()).concat(" ")
				.concat(documento.getApeMaterno());
		return nombreCompleto;

	}

//	public static String getClientIpAddress() {
//		try {
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//					.getRequest();
//			for (String header : IP_HEADER_CANDIDATES) {
//				String ip = request.getHeader(header);
//				if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
//					return ip;
//				}
//			}
//			return request.getRemoteAddr();
//		} catch (Exception e) {
//			// TODO: handle exception
//			return "";
//		}
//	}

}
