package pe.gob.serfor.osutd.sgd.repository.util;

import org.apache.commons.lang3.StringUtils;

public class Util {

	public static String getUid() throws Exception {
		String uuidStr = StringUtils.EMPTY;

		try {

			uuidStr = java.util.UUID.randomUUID().toString();
			uuidStr = uuidStr.replaceAll("-", "");
			uuidStr = uuidStr.substring(0, 32);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uuidStr;
	}

	public static String contentTypeStr(String extension) {
		String contetType = StringUtils.EMPTY;

		try {

			switch (extension.toUpperCase()) {
			case "PDF":
				contetType = Constantes.CONTENT_TYPE_PDF;
				break;
			case "DOC":
				contetType = Constantes.CONTENT_TYPE_DOC;
				break;
			case "DOCX":
				contetType = Constantes.CONTENT_TYPE_DOCX;
				break;
			case "XLS":
				contetType = Constantes.CONTENT_TYPE_XLS;
				break;
			case "XLSX":
				contetType = Constantes.CONTENT_TYPE_XLSX;
				break;
			case "JPG":
				contetType = Constantes.CONTENT_TYPE_JPG;
				break;
			case "JPEG":
				contetType = Constantes.CONTENT_TYPE_JPEG;
				break;
			case "3GP":
				contetType = Constantes.CONTENT_TYPE_3GP;
				break;
			case "AVI":
				contetType = Constantes.CONTENT_TYPE_AVI;
				break;
			case "MPG":
				contetType = Constantes.CONTENT_TYPE_MPG;
				break;
			case "MPEG":
				contetType = Constantes.CONTENT_TYPE_MPG;
				break;
			case "MP4":
				contetType = Constantes.CONTENT_TYPE_MP4;
				break;
			case "FLV":
				contetType = Constantes.CONTENT_TYPE_FLV;
				break;
			case "WMV":
				contetType = Constantes.CONTENT_TYPE_WMV;
				break;
			case "MOV":
				contetType = Constantes.CONTENT_TYPE_MOV;
				break;
			case "SHP":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "SHX":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "DBF":
				contetType = Constantes.CONTENT_TYPE_DBF;
				break;
			case "SBN":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "SBX":
				contetType = Constantes.CONTENT_TYPE_SBX;
				break;
			case "IXS":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "PRJ":
				contetType = Constantes.CONTENT_TYPE_PRJ;
				break;
			case "MXS":
				contetType = Constantes.CONTENT_TYPE_MXS;
				break;
			case "FBN":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "FBX":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "AIN":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "AIH":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "IMG":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			case "DWG":
				contetType = Constantes.CONTENT_TYPE_DWG;
				break;
			case "DXF":
				contetType = Constantes.CONTENT_TYPE_DXF;
				break;
			case "DWF":
				contetType = Constantes.CONTENT_TYPE_DWF;
				break;
			case "DGN":
				contetType = Constantes.CONTENT_TYPE_OBJECT;
				break;
			default:
				break;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contetType.toString();
	}

	public static final String uuid() {
		String result = java.util.UUID.randomUUID().toString();

		result = result.replaceAll("-", "");
		result = result.substring(0, 32);

		return result;
	}

}
