package pe.gob.serfor.osutd.sgd.repository.logic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//import pe.gob.serfor.wssisged.logic.dao.ParametroDao;

public class MjeValidacionProperties {

	static Properties propiedades = null;

	static Properties getProperties() {
		if (propiedades == null) {
			//System.out.println("ingresando propiedades");
			try {
				StringBuilder ruta = new StringBuilder();
				ruta.append(ConstantesUtil.URL_RUTA_RECURSOS);
				// ruta.append(parametrodao.obtenerRuta().getRuta());
				ruta.append(File.separator);

				ruta.append("mensajeValidacion.properties.properties");
				System.out.println(ruta.toString()+" mensajeValidacion.properties");
				propiedades = new Properties();
				propiedades.load(new FileInputStream(ruta.toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return propiedades;
	}

	public static String AUTORIZADO_CODIGO = getProperties().getProperty("autorizado.codigo");
	public static final String AUTORIZADO_DESCRIPCION = getProperties().getProperty("autorizado.descripcion");
	public static final String ERROR_NOAUTORIZADO_CODIGO = getProperties().getProperty("error.noautorizado.codigo");
	public static final String ERROR_NOAUTORIZADO_DESCRIPCION = getProperties()
			.getProperty("error.noautorizado.descripcion");
	public static final String ERROR_CAMPO_INGRESADO_VACIO_CODIGO = getProperties()
			.getProperty("error.parametroentrada.vacio.codigo");
	public static final String ERROR_CAMPO_FORMATOINCORRECTO_CODIGO = getProperties()
			.getProperty("error.parametroentrada.formatoincorrecto.codigo");
	public static final String ERROR_REGISTROSNOENCONTRADOS_CODIGO = getProperties()
			.getProperty("error.registrosnoencontrados.codigo");
	public static final String ERROR_REGISTROSNOENCONTRADOS_DESCRIPCION = getProperties()
			.getProperty("error.registrosnoencontrados.descripcion");
	public static final String ERROR_SEGURIDIAD_CAMPO_USUARIO_VACIO = getProperties()
			.getProperty("error.parametroentrada.vacio.descripcion.usuario");
	public static final String ERROR_SEGURIDAD_CAMPO_CLAVE_VACIO = getProperties()
			.getProperty("error.parametroentrada.vacio.descripcion.clave");
	public static final String ERROR_CAMPO_PERIODO_VACIO = getProperties()
			.getProperty("error.parametroentrada.vacio.descripcion.periodo");
	public static final String ERROR_CAMPO_NROCUT_VACIO = getProperties()
			.getProperty("error.parametroentrada.vacio.descripcion.nrocut");
	public static final String ERROR_CAMPO_NROCUD_VACIO = getProperties()
			.getProperty("error.parametroentrada.vacio.descripcion.nrocud");
	public static final String ERROR_CAMPO_NROCUT_FORMATO_INCORRECTO = getProperties()
			.getProperty("error.parametroentrada.formatoincorrecto.nrocut");
	public static final String ERROR_CAMPO_PERIODO_FORMATO_INCORRECTO = getProperties()
			.getProperty("error.parametroentrada.formatoincorrecto.periodo");
	public static final String ERROR_CAMPO_UNIDAD_ORGANICA_VACIO = getProperties()
			.getProperty("error.parametroentrada.vacio.unidadorganica");
	public static final String ERROR_CAMPO_UNIDAD_ORGANICA_FORMATO_INCORRECTO = getProperties()
			.getProperty("error.parametroentrada.formatoincorrecto.unidadorganica");

	public static final String ERROR_REGISTROSNOENCONTRADOS_CUT_DESCRIPCION = getProperties()
			.getProperty("error.registrosnoencontrados.cud.descripcion");

	public static final String ERROR_INESPERADO_CODIGO = getProperties().getProperty("error.inesperado.codigo");
	public static final String ERROR_INESPERADO = getProperties().getProperty("error.inesperado");

}
