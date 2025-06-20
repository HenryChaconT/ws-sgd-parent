package pe.gob.serfor.osutd.sgd.rest.server;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.json.JSONException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDepartamento;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDistrito;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroProvincia;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UbigeoResponse;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UbigeoResponseProvincia;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MjeValidacionProperties;
import pe.gob.serfor.osutd.sgd.service.logic.UbigeoService;
import pe.gob.serfor.osutd.sgd.service.logic.UsuarioService;

//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDepartamento;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDistrito;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroProvincia;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UbigeoResponse;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UbigeoResponseProvincia;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.service.UbigeoService;
//import pe.gob.serfor.wssisged.logic.service.UsuarioService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.MjeValidacionProperties;

@RestController
@RequestMapping(value = "/sgd/ubigeo")
public class WsUbigeo extends SpringBeanAutowiringSupport {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private UbigeoService ubigeoService;

	@Autowired
	private UsuarioService usuarioService;

//	@GET
//	@Path("/listarDepartamento")
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@GetMapping(path = {"/listarDepartamento"})
	public UbigeoResponse listarDepartamento(/*@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,
			@HeaderParam("idAplicacion") String idAplicacion,*/ @QueryParam("codDep") String codDep,
			@QueryParam("desDep") String desDep) throws IOException, JSONException {

		Mensaje resultado = null;
		UbigeoResponse ubigeoresponse = new UbigeoResponse();
		try {
//			if (idAplicacion != null && !idAplicacion.trim().equals(""))
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave,
//						Integer.parseInt(idAplicacion.trim()));
//			else {
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
//			}
//
//			if (resultado != null) {
//				ubigeoresponse.setMensaje(resultado);
//				return ubigeoresponse;
//			}

			ParametroDepartamento parametro = new ParametroDepartamento();
			parametro.setCodDep(StringUtils.isEmpty(codDep) ? null : codDep);
			parametro.setDescripcion(StringUtils.isEmpty(desDep) ? null : desDep);

			ubigeoresponse = ubigeoService.buscarDepartamento(parametro);
			resultado = new Mensaje();
			if (ubigeoresponse == null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				ubigeoresponse = new UbigeoResponse();
				ubigeoresponse.setMensaje(resultado);
			} else {
				resultado = new Mensaje();
				resultado.setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
				resultado.setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
				ubigeoresponse.setMensaje(resultado);

			}

		} catch (Exception ex) {
			log.error("Error", ex);

			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			ubigeoresponse.setMensaje(resultado);
			return ubigeoresponse;
		}
		return ubigeoresponse;
	}

//	@GET
//	@Path("/listarProvincia")
//	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@GetMapping(path = {"/listarProvincia"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public String listarProvincia(/*@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,
			@HeaderParam("idAplicacion") String idAplicacion,*/ @QueryParam("codDep") String codDep,
			@QueryParam("desDep") String desDep, @QueryParam("desProv") String desProv,
			@QueryParam("codProv") String codProv) throws IOException, JSONException {

		Mensaje resultado = null;
		UbigeoResponseProvincia ubigeoresponse = new UbigeoResponseProvincia();
		try {
//			if (idAplicacion != null && !idAplicacion.trim().equals(""))
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave,
//						Integer.parseInt(idAplicacion.trim()));
//			else {
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
//			}
//
//			if (resultado != null) {
//				ubigeoresponse.setMensaje(resultado);
//
//				ObjectMapper mapper = new ObjectMapper();
//				String jsonInString = mapper.writeValueAsString(ubigeoresponse);
//				return jsonInString;
//			}

			ParametroProvincia parametroProvincia = new ParametroProvincia();
			parametroProvincia.setCodDep(StringUtils.isEmpty(codDep) ? null : codDep);
			parametroProvincia.setDesDepartamento(StringUtils.isEmpty(desDep) ? null : desDep);
			parametroProvincia.setDesProvincia(StringUtils.isEmpty(desProv) ? null : desProv);
			parametroProvincia.setCodProv(StringUtils.isEmpty(codProv) ? null : codProv);
			// CodProv

			ubigeoresponse = ubigeoService.buscarProvincia(parametroProvincia);
			resultado = new Mensaje();
			if (ubigeoresponse.getLstUbigeo() != null && !ubigeoresponse.getLstUbigeo().isEmpty()) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				ubigeoresponse.setMensaje(resultado);
			} else {
				resultado = new Mensaje();
				resultado.setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
				resultado.setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
				ubigeoresponse.setMensaje(resultado);

			}

		} catch (Exception ex) {
			log.error("Error", ex);

			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			ubigeoresponse.setMensaje(resultado);

		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(ubigeoresponse);
		return jsonInString;
	}

//	@GET
//	@Path("/listarDistrito")
//	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@GetMapping(path = {"/listarDistrito"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public /* UbigeoResponse */ String listarDistrito(/*@HeaderParam("usuario") String usuario,
			@HeaderParam("clave") String clave, @HeaderParam("idAplicacion") String idAplicacion,*/
			@QueryParam("codDep") String codDep, @QueryParam("desDep") String desDep,
			@QueryParam("codProv") String codProv, @QueryParam("desProv") String desProv,
			@QueryParam("desDist") String desDist) throws IOException, JSONException {

		Mensaje resultado = null;
		UbigeoResponse ubigeoresponse = new UbigeoResponse();
		try {
//			if (idAplicacion != null && !idAplicacion.trim().equals(""))
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave,
//						Integer.parseInt(idAplicacion.trim()));
//			else {
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
//			}
//
//			if (resultado != null) {
//				ubigeoresponse.setMensaje(resultado);
////				 return ubigeoresponse;
//				ObjectMapper mapper = new ObjectMapper();
//				String jsonInString = mapper.writeValueAsString(ubigeoresponse);
//				return jsonInString;
//			}

			ParametroDistrito parametroDistrito = new ParametroDistrito();
			parametroDistrito.setCodDep(StringUtils.isEmpty(codDep) ? null : codDep);
			parametroDistrito.setDesDepartamento(StringUtils.isEmpty(desDep) ? null : desDep);
			parametroDistrito.setCodProv(StringUtils.isEmpty(codProv) ? null : codProv);
			parametroDistrito.setDesProvincia(StringUtils.isEmpty(desProv) ? null : desProv);
			parametroDistrito.setDesDistrito(StringUtils.isEmpty(desDist) ? null : desDist);

			ubigeoresponse = ubigeoService.buscarDistrito(parametroDistrito);
			resultado = new Mensaje();
			if (ubigeoresponse.getMensaje() != null) {
				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
				ubigeoresponse.setMensaje(resultado);
			} else {
				resultado = new Mensaje();
				resultado.setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
				resultado.setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
				ubigeoresponse.setMensaje(resultado);

			}

		} catch (Exception ex) {
			log.error("Error", ex);

			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			ubigeoresponse.setMensaje(resultado);
//			return ubigeoresponse;
		}
//		return ubigeoresponse;

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(ubigeoresponse);
		return jsonInString;
	}

}
