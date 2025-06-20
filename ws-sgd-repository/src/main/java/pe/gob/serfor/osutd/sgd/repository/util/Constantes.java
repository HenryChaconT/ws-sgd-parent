package pe.gob.serfor.osutd.sgd.repository.util;

import java.io.Serializable;

public class Constantes implements Serializable {

//	public final static String UPLOADED_FOLDER = "d://solicitudes//documentos//";
	public final static String UPLOADED_FOLDER = "/mnt/imagen_remoto/prueba";


	private static final long serialVersionUID = 1L;
	
	public final static String MESSAGE_ERROR_500 = "Estamos teniendo problemas. Por favor comuníquese con el administrador del sistema.";
	
	/** FORMATO DE FECHAS **/
	public final static String HORA_FORMATO = "HH:mm:ss";
	public final static String FECHA_FORMATO = "dd/MM/yyyy";
	public final static String FECHA_FORMATO_HORAS = "dd/MM/yyyy HH:mm:ss";
	public final static String HORA_VALUE_INICIO_DEFAULT = "00:00:00";
	public final static String HORA_VALUE_FINAL_DEFAULT = "23:59:59";
	
	public static final Character ESTADO_ACTIVO = '1';
	public static final Character ESTADO_INACTIVO = '0';
		
	public final static String MESSAGE_SUCCESS = "Operation Success! ";
	public final static String MESSAGE_ERROR = "Operation Failed! ";
	
	public final static boolean STATUS_SUCCESS = Boolean.TRUE;
	public final static boolean STATUS_ERROR = Boolean.FALSE;
	
	public final static String NO_EXISTE_DOCUMENTO_ADJUNTO = "Debe adjuntar un documento.";
	public static final Character SI = '1';
	public static final Character NO = '0';
	public final static String USER_SYSTEM = "SYSTEM";

	public final static String PUNTO = ".";
	public final static String DOBLE_BACKSLASH = "//";
	public final static String MESSAGE_IDENTIFICADOR_NO_EXISTE = "Debe enviar el identificador del archivo";
	public final static long PESO_FILE_BYTES = 83886080;
	
	public final static String[] EXTENSIONES_UPLOAD = { "pdf", "doc", "docx", "xls", "xlsx", "jpg", "jpeg", "3gp", "avi", "mpg", "mpeg", "mp4", "flv", "wmv", "mov", "shp", "shx", "dbf", "sbn", "sbx", "ixs", "prj", "mxs", "fbn", "fbx","ain", "aih", "img", "dwg", "dxf", "dwf", "dgn" };
		
	public final static String CONTENT_TYPE_PDF = "application/pdf";
	public final static String CONTENT_TYPE_DOC = "application/msword";
	public final static String CONTENT_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	public final static String CONTENT_TYPE_XLS = "application/vnd.ms-excel";
	public final static String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public final static String CONTENT_TYPE_JPG = "image/jpg";
	public final static String CONTENT_TYPE_JPEG = "image/jpeg";
	
	public final static String CONTENT_TYPE_3GP = "video/3gpp";
	public final static String CONTENT_TYPE_AVI = "video/x-msvideo";
	public final static String CONTENT_TYPE_MPG = "audio/mpeg";
	public final static String CONTENT_TYPE_MP4 = "video/mp4";
	public final static String CONTENT_TYPE_FLV = "video/x-flv";
	public final static String CONTENT_TYPE_WMV = "video/x-ms-wmv";
	public final static String CONTENT_TYPE_MOV = "video/quicktime";
	public final static String CONTENT_TYPE_OBJECT = "application/octet-stream";
	public final static String CONTENT_TYPE_DBF = "application/x-dbf";
	public final static String CONTENT_TYPE_SBX = "application/x-sbx";
//	public final static String CONTENT_TYPE_IXS = "";
	public final static String CONTENT_TYPE_PRJ = "application/x-cdf";
	public final static String CONTENT_TYPE_MXS = "application/vnd.triscape.mxs";
//	public final static String CONTENT_TYPE_FBN = "";
//	public final static String CONTENT_TYPE_FBX = "";
//	public final static String CONTENT_TYPE_AIN = "";
//	public final static String CONTENT_TYPE_AIH = "";
//	public final static String CONTENT_TYPE_IMG = "";
	public final static String CONTENT_TYPE_DWG = "image/vnd.dwg";
	public final static String CONTENT_TYPE_DXF = "image/vnd.dxf";
	public final static String CONTENT_TYPE_DWF = "model/vnd.dwf";
//	public final static String CONTENT_TYPE_DGN = "";
	
	public final static String MESSAGE_EXTENSIONES_PERMITIDAS = "El archivo debe ser de extensión: .pdf, .doc, .docx, .xls, .xlsx, .jpg, .jpeg, .3gp, .avi, .mpg, .mpeg, .mp4, .flv, .wmv, .mov, .shp, .shx, .dbf, .sbn, .sbx, .ixs, .prj, .mxs, .fbn, .fbx, .ain, .aih, .img, .dwg, .dxf, .dwf, .dgn";
	public final static String MESSAGE_PESO_MAYOR_10MB = "El peso del archivo no debe ser mayor a 80 MB";
	public final static String MESSAGE_LONGITUD_TEXTO_EXCEDIDO = "La longitud del nombre del archivo no debe ser mayor a 100 carácteres.";
	
	public final static String MESSAGE_FECHA_INICIO_NO_EXISTE = "Debe ingresar una fecha de inicio.";
	public final static String MESSAGE_FECHA_FIN_NO_EXISTE = "Debe ingresar una fecha final.";

	/** ESTADOS DE LOS DOCUMENTOS **/
	public final static String ACCION_REGISTRADO = "REGISTRADO";
	public final static String ACCION_ACEPTADO = "ACEPTADO";
	public final static String ACCION_OBSERVADO = "OBSERVADO";
	public final static String ACCION_SUBSANADO = "SUBSANADO";
	
	public final static String MESSAGE_NO_EXISTE_RESULTADO = "No se encontró resultados con los parámetos ingresados";
	
	/** SERVICIOS WEB DEL SGD **/
	//public final static String SERVICIO_SGD_PATH_BASE = "http://ws.serfor.gob.pe/sisgedwsprueba/apiSerfor/sgd/";
    public final static String SERVICIO_SGD_PATH_BASE = "http://192.168.100.240:8080/ws-sgd/sgd/";
	
	public final static String SERVICIO_SGD_TRAZABILIDAD_POR_EXPEDIENTE = SERVICIO_SGD_PATH_BASE.concat("pas/buscarDocumentos/");
	public final static String SERVICIO_SGD_BUSCAR_EXPEDIENTES = SERVICIO_SGD_PATH_BASE.concat("pas/buscarDocumentosMesaPartes/");
	public final static String SERVICIO_SGD_FILE_DOCUMENTO_PRINCIPAL = SERVICIO_SGD_PATH_BASE.concat("pas/descargarArchivoPrincipal/");
	public final static String SERVICIO_SGD_OBTENER_ANEXOS_POR_DOCUMENTO = SERVICIO_SGD_PATH_BASE.concat("pas/buscarAnexos");
	public final static String SERVICIO_SGD_FILE_ANEXO = SERVICIO_SGD_PATH_BASE.concat("pas/descargarAnexo/");
	public final static String SERVICIO_SGD_LOGIN = SERVICIO_SGD_PATH_BASE.concat("usuario/login/");
		
	public final static String USUARIO_API_SGD = "ADMIN";
	public final static String CLAVE_API_SGD = "Agro2025%";
	
	// TIPOS DE BUSQUEDA SGD
	public final static String BUSQUEDA_DETALLE_EXPEDIENTE = "2";
	public final static String BUSQUEDA_EXPEDIENTES_POR_DNI = "4";
	public final static String BUSQUEDA_EXPEDIENTES_POR_OTRO = "4";
	
	/** WEBSERVICE CONSULTA DE DATOS RESERVADOS **/	
	public final static String SERVICIO_WEB_CONSULTA_DATOS_RESERVADOS = "http://192.168.100.205/serviciosPide/reniec/ObtenerDatosDniReniec?";
	
	//
	public final static String ENLACE_MPV_PATH_BASE = "http://192.168.100.240:8080/componente-documentario-war-1.0.1/";
//	public final static String ENLACE_MPV_PATH_BASE = "http://10.6.0.146:8282/mesadepartesvirtual/";
	public final static String ENLACE_ACTIVACION_CUENTA = ENLACE_MPV_PATH_BASE.concat("user/authorization?c=");
	public final static String ENLACE_PAGINA_PRINCIPAL = ENLACE_MPV_PATH_BASE.concat("#/");
}
