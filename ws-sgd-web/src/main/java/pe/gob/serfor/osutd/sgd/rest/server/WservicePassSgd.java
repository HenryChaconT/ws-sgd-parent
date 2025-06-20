package pe.gob.serfor.osutd.sgd.rest.server;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;

import javax.ws.rs.POST;

import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.json.JSONException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.AnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ArchivoPrincipalBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentoPrincipalBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosSgdBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MjeValidacionProperties;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Util;
import pe.gob.serfor.osutd.sgd.repository.util.Urls;
import pe.gob.serfor.osutd.sgd.service.logic.DocSgdService;
import pe.gob.serfor.osutd.sgd.service.logic.UsuarioService;



@RestController
@RequestMapping(Urls.consultas.BASE)
public class WservicePassSgd extends SpringBeanAutowiringSupport {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private DocSgdService docSgdService;
	@Autowired
	UsuarioService usuarioService;


	@GetMapping(path = {"/buscarDocumento"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public DocumentoPrincipalBean buscarDocumento(@RequestBody ParametrosSgdBean parametro) {
		Mensaje resultado = null;
		DocumentoPrincipalBean documento = null;
		try {


			resultado = pasoValidacionesBuscarDocumento(parametro);
			if (resultado != null) {
				documento = new DocumentoPrincipalBean();
				documento.setMensaje(resultado);
				return documento;
			}

			documento = docSgdService.buscarDocumento(parametro);
			if (documento.getMensaje() == null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				documento.setMensaje(resultado);
			}

		} catch (Exception ex) {
			this.log.error("Error", ex);
			documento = new DocumentoPrincipalBean();
			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			documento.setMensaje(resultado);
			return documento;
		}
		return documento;
	}

	Mensaje pasoValidacionesBuscarDocumento(ParametrosSgdBean parametro) throws Exception {

		Mensaje mensaje = new Mensaje();
		if (Util.esVacio(parametro.getNumDocumento()) && Util.esVacio(parametro.getNumEmi())) {

			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo Número de documento o número de Emisión no pueden ser vacio");
			return mensaje;
		}
		if (!Util.esVacio(parametro.getNumEmi()) && !Util.esNumerico(parametro.getNumEmi())) {
			mensaje.setCodigo(String.valueOf(ConstantesUtil.ERR_CAMPO_DISTINTO_FORMATO));
			mensaje.setDesMensaje("Campo Nro. Emisión deber ser numérico ");
			return mensaje;
		}
		if (!Util.esVacio(parametro.getAnio()) && !Util.esNumerico(parametro.getAnio())) {
			mensaje.setCodigo(String.valueOf(ConstantesUtil.ERR_CAMPO_DISTINTO_FORMATO));
			mensaje.setDesMensaje("Campo año deber ser numérico ");
			return mensaje;
		}
		if (Util.esVacio(parametro.getAnio())) {

			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo año no puede ser vacío ");
			return mensaje;

		}
		return null;

	}

	public Mensaje pasoValidacionesListarDocumento(ParametrosSgdBean parametro) throws Exception {

		Mensaje mensaje = new Mensaje();
		if (Util.esVacio(parametro.getNumExpediente()) && parametro.getTipoBusqueda() != null
				&& parametro.getTipoBusqueda().equals("2")) {

			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje("Campo Número de Expediente no puede ser vacío");
			return mensaje;
		}
		if (Util.esVacio(parametro.getNumEmi()) && parametro.getTipoBusqueda() != null
				&& parametro.getTipoBusqueda().equals("3")) {

			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje("Campo Número de Emisión no puede ser vacío");
			return mensaje;
		}
		if (!Util.esNumerico(parametro.getNumEmi()) && parametro.getTipoBusqueda() != null
				&& parametro.getTipoBusqueda().equals("3")) {

			mensaje.setCodigo(ConstantesUtil.ERR_CAMPO_DISTINTO_FORMATO);
			mensaje.setDesMensaje("Campo Número de Emisión con caracteres distinto a numérico");
			return mensaje;
		}

		if (Util.esVacio(parametro.getAnio())) {

			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje("Campo año no puede ser vacío");
			return mensaje;
		}
		if (!Util.esVacio(parametro.getAnio()) && !Util.esNumerico(parametro.getAnio())) {
			mensaje.setCodigo(String.valueOf(ConstantesUtil.ERR_CAMPO_DISTINTO_FORMATO));
			mensaje.setDesMensaje("Campo año deber ser numérico ");
			return mensaje;
		}
		return null;
	}

	public Mensaje pasoValidacionesListarDocumentoMP(ParametrosMPBean parametro) throws Exception {

		Mensaje mensaje = new Mensaje();
		if (parametro.getTipoBusqueda() != null && parametro.getTipoBusqueda().equals("2")) {
			if (Util.esVacio(parametro.getNumExpediente())) {

				mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
				mensaje.setDesMensaje("Campo Número de Expediente no puede ser vacío");
				return mensaje;
			}

		}

		if (parametro.getTipoBusqueda() != null && parametro.getTipoBusqueda().equals("4")) {

			if (Util.esVacio(parametro.getNumDocIden())) {

				mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
				mensaje.setDesMensaje("Campo Número de  DNI no puede ser vacío");
				return mensaje;
			}
			if (!Util.esNumerico(parametro.getNumDocIden())) {

				mensaje.setCodigo(ConstantesUtil.ERR_CAMPO_DISTINTO_FORMATO);
				mensaje.setDesMensaje("Campo Número de  DNI tiene formato incorrecto");
				return mensaje;
			}

		}
		if (parametro.getTipoBusqueda() != null && parametro.getTipoBusqueda().equals("5")) {

			if (Util.esVacio(parametro.getNumDocIden())) {

				mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
				mensaje.setDesMensaje("Campo Número de RUC no puede ser vacío");
				return mensaje;
			}
			if (!Util.esNumericoLong(parametro.getNumDocIden())) {

				mensaje.setCodigo(ConstantesUtil.ERR_CAMPO_DISTINTO_FORMATO);
				mensaje.setDesMensaje("Campo Número de RUC tiene formato incorrecto");
				return mensaje;
			}

		}

		if (Util.esVacio(parametro.getAnio())) {

			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje("Campo año no puede ser vacío");
			return mensaje;
		}
		if (!Util.esVacio(parametro.getAnio()) && !Util.esNumerico(parametro.getAnio())) {
			mensaje.setCodigo(String.valueOf(ConstantesUtil.ERR_CAMPO_DISTINTO_FORMATO));
			mensaje.setDesMensaje("Campo año deber ser numérico ");
			return mensaje;
		}
		return null;
	}

//	@POST
//	@Path("/buscarDocumentos")
//	@Consumes({ "application/json" })
//	@Produces({ "application/json" })
	@PostMapping(path = {"/buscarDocumentos"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public DocumentosBean buscarDocumentos(@RequestBody ParametrosSgdBean parametro) {
		Mensaje resultado = null;
		DocumentosBean documento = null;
		try {

			resultado = pasoValidacionesListarDocumento(parametro);
			if (resultado != null) {
				documento = new DocumentosBean();
				documento.setMensaje(resultado);
				return documento;
			}
			documento = docSgdService.listarDocumento(parametro);
			if (documento.getMensaje() == null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				documento.setMensaje(resultado);
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			documento = new DocumentosBean();
			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			documento.setMensaje(resultado);
			return documento;
		}

		return documento;
	}

//	@POST
//	@Path("/buscarDocumentosMesaPartes")
//	@Consumes({ "application/json" })
//	@Produces({ "application/json" })
	@PostMapping(path = {"/buscarDocumentosMesaPartes"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public DocumentosMPBean buscarDocumentosMesaPartes(@HeaderParam("usuario") String usuario,
			@HeaderParam("clave") String clave,@RequestBody ParametrosMPBean parametro) {
		Mensaje resultado = null;
		DocumentosMPBean documento = null;
		try {

//			resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
//			if (resultado != null) {
//				documento = new DocumentosMPBean();
//				documento.setMensaje(resultado);
//				return documento;
//			}

			resultado = pasoValidacionesListarDocumentoMP(parametro);
			if (resultado != null) {
				documento = new DocumentosMPBean();
				documento.setMensaje(resultado);
				return documento;
			}

			documento = docSgdService.listarDocumentosMesaPartes(parametro);
			if (documento.getMensaje() == null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				documento.setMensaje(resultado);
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			documento = new DocumentosMPBean();
			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			documento.setMensaje(resultado);
			return documento;
		}
		//System.out.println(documento.getDocumentos().get(0).toString()+ " kjdsf");
		return documento;
	}


	@GetMapping(path = {"/descargarArchivoPrincipal/"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public String descargarArchivoPrincipal(@RequestParam("numEmi") String numEmi,
			@RequestParam("anio") String anio) throws IOException, JSONException {

		Mensaje resultado = null;
		ArchivoPrincipalBean documento = null;
		ParametrosBusqBean parametro = new ParametrosBusqBean();
		parametro.setAnio(anio);
		parametro.setNumEmi(numEmi);
		ObjectMapper mapper = new ObjectMapper();

		try {
			resultado = pasoValidacionesArchivoPrincipal(parametro);

			if (resultado != null) {
				documento = new ArchivoPrincipalBean();
				documento.setMensaje(resultado);
				return mapper.writeValueAsString(documento);
				// return documento;
			}
//			if (idAplicacion != null && !idAplicacion.trim().equals("")) {
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave,
//						Integer.parseInt(idAplicacion.trim()));
//			} else {
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
//			}

//			if (resultado != null) {
//				documento = new ArchivoPrincipalBean();
//				documento.setMensaje(resultado);
//				// return documento;
//				return mapper.writeValueAsString(documento);
//			}
			documento = docSgdService.descargarArchivoPrincipal(parametro);
			if (documento.getMensaje() == null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				documento.setMensaje(resultado);
			}

		} catch (Exception ex) {
			this.log.error("Error", ex);
			documento = new ArchivoPrincipalBean();
			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			documento.setMensaje(resultado);
			return mapper.writeValueAsString(documento);
			// return documento;
		}
		// return documento;
		return mapper.writeValueAsString(documento);
	}

	private Mensaje pasoValidacionesArchivoPrincipal(ParametrosBusqBean parametro) throws Exception {

		Mensaje mensaje = new Mensaje();
		if (Util.esVacio(parametro.getNumEmi())) {

			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo  número de Emisión no pueden ser vacio");
			return mensaje;
		}
		if (!Util.esVacio(parametro.getNumEmi()) && !Util.esNumerico(parametro.getNumEmi())) {
			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo año no puede ser vacío ");
			return mensaje;
		}
		if (Util.esVacio(parametro.getAnio())) {

			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo año no puede ser vacío ");
			return mensaje;

		}
		return null;

	}

	Mensaje pasoValidacionesBuscarAnexos(ParametrosBusqBean parametro) throws Exception {

		Mensaje mensaje = new Mensaje();
		if (Util.esVacio(parametro.getNumEmi())) {

			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo  número de Emisión no pueden ser vacio");
			return mensaje;
		}
		if (!Util.esVacio(parametro.getNumEmi()) && !Util.esNumerico(parametro.getNumEmi())) {
			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo año no puede ser vacío ");
			return mensaje;
		}
		if (Util.esVacio(parametro.getAnio())) {

			mensaje.setCodigo(String.valueOf(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO));
			mensaje.setDesMensaje("Campo año no puede ser vacío ");
			return mensaje;

		}
		return null;

	}


	@GetMapping(path = {"/buscarAnexos/"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public DocumentosAnexoBean buscarAnexos( @RequestParam("numEmi") String numEmi,
			@RequestParam("anio") String anio) throws IOException, JSONException {

		Mensaje resultado = null;
		DocumentosAnexoBean documento = null;
		try {
			ParametrosBusqBean parametro = new ParametrosBusqBean();
			parametro.setNumEmi(numEmi);
			parametro.setAnio(anio);

			resultado = pasoValidacionesBuscarAnexos(parametro);
			if (resultado != null) {
				documento = new DocumentosAnexoBean();
				documento.setMensaje(resultado);
				return documento;
			}

			documento = docSgdService.listarAnexos(parametro);
			if (documento.getMensaje() == null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				documento.setMensaje(resultado);
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			documento = new DocumentosAnexoBean();
			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			documento.setMensaje(resultado);
			return documento;
		}
		return documento;
	}


	@GetMapping(path = {"/descargarAnexo/"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public AnexoBean descargarAnexo( @RequestParam("numEmi") String numEmi,
			@RequestParam("anio") String anio, @RequestParam("nuAne") String nuAne) throws IOException, JSONException {

		Mensaje resultado = null;
		AnexoBean documento = null;
		try {
			ParametrosBusqAnexoBean parametro = new ParametrosBusqAnexoBean();
			parametro.setNumEmi(numEmi);
			parametro.setAnio(anio);
			parametro.setNuAne(nuAne);


			documento = docSgdService.descargarAnexo(parametro);
			if (documento.getMensaje() == null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				documento.setMensaje(resultado);
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			documento = new AnexoBean();
			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			documento.setMensaje(resultado);
			return documento;
		}
		return documento;
	}

}
