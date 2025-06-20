package pe.gob.serfor.osutd.sgd.rest.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//import pe.gob.serfor.wssisged.logic.bean.integracion.*;
//import pe.gob.serfor.wssisged.logic.bean.integracion.response.DocumentoGeneradoModelBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.response.ResponseBean;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.service.DocSgdInteService;
//import pe.gob.serfor.wssisged.logic.service.UsuarioService;
//
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;
//import pe.gob.serfor.wssisged.utils.MjeValidacionProperties;
//import pe.gob.serfor.wssisged.utils.Util;

import org.springframework.web.multipart.MultipartFile;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ActualizaAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.AnexoParametroBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CitizenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocExternoRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.InsertaAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ProveedorBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.response.DocumentoGeneradoModelBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.response.ResponseBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MjeValidacionProperties;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Util;
import pe.gob.serfor.osutd.sgd.service.logic.DocSgdInteService;
import pe.gob.serfor.osutd.sgd.service.logic.UsuarioService;

@RestController
@RequestMapping(value ="/sgd/integracion")
public class WsServiceIntegracion extends SpringBeanAutowiringSupport {

	@Autowired
	DocSgdInteService _docSgdInteService;
	@Autowired
	UsuarioService usuarioService;

	protected final Log log = LogFactory.getLog(getClass());

	/*
	 * TRAMITE
	 */
	/*@POST
	@Path("/tramite")
	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PostMapping(path = {"/tramite"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public DocumentoGeneradoModelBean insertarTramite(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestBody DocExternoRecepBean model) {
		DocumentoGeneradoModelBean response = new DocumentoGeneradoModelBean();
		try {
			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				// Usuario usuarioTransaccion = new Usuario();
				Usuario userSession = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (userSession != null) {
					// VALIDAR REGLA DE NEGOCIO
					model.setCoUserMod(userSession.getCOD_USER());
					model.setCempCodEmp(userSession.getCEMP_CODEMP());
					model.setCoDependencia(userSession.getCO_DEPENDENCIA());
					userSession.setDeIpPc(this.getClientIpAddress());
					userSession.setTiProceso("R");
					userSession.setEsProceso("5");

					// PROCESO DE REGISTRO EN BD
					MethodResponseBean<String> result = new MethodResponseBean<String>();
					result = _docSgdInteService.Add_DocumentoExternoRecep(model, userSession);
					if (result.getSuccess()) {
						response.setEsDocEmi(model.getDocumentoEmiBean().getDeEsDocEmi());
						response.setNuAnn(model.getDocumentoEmiBean().getNuAnn());
						response.setNuAnnExp(model.getDocumentoEmiBean().getNuAnnExp());
						response.setNuCorDoc(model.getDocumentoEmiBean().getNuCorDoc());
						response.setNuCorrExp(model.getDocumentoEmiBean().getNuCorrExp());
						response.setNuEmi(model.getDocumentoEmiBean().getNuEmi());
						response.setNuExpediente(model.getDocumentoEmiBean().getNuExpediente());
						response.setNuSecExp(model.getDocumentoEmiBean().getNuSecExp());
						response.setCode("0");
						response.setMessage("Se Registró correctamente el trámite");
					} else {
						response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
						response.setMessage(result.getMessage());
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*@PUT
	@Path("/tramite")
	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping(path = {"/tramite"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public DocumentoGeneradoModelBean updateTramite(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("idAplicacion") String idAplicacion,
			@RequestHeader("clave") String clave,@RequestBody DocExternoRecepBean model) {
		DocumentoGeneradoModelBean response = new DocumentoGeneradoModelBean();
		try {
			Mensaje resultado = null;
			if (idAplicacion != null && !idAplicacion.trim().equals(""))
				resultado = usuarioService.validarCredencialesSGD(usuario, clave,
						Integer.parseInt(idAplicacion.trim()));
			else {
				resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
			}

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				// Usuario usuarioTransaccion = new Usuario();
				// VALIDAR REGLA DE NEGOCIO
				Usuario userSession = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (userSession != null) {
					// VALIDAR REGLA DE NEGOCIO
					model.setCoUserMod(userSession.getCOD_USER());
					model.setCempCodEmp(userSession.getCEMP_CODEMP());
					model.setCoDependencia(userSession.getCO_DEPENDENCIA());
					userSession.setDeIpPc(this.getClientIpAddress());
					userSession.setTiProceso("R");
					userSession.setEsProceso("5");

					// PROCESO DE REGISTRO EN BD
					MethodResponseBean<String> data = new MethodResponseBean<String>();

					data = _docSgdInteService.Update_DocumentoExternoRecep(model, userSession);
					if (data.getSuccess()) {
						response.setEsDocEmi(model.getDocumentoEmiBean().getDeEsDocEmi());
						response.setNuAnn(model.getDocumentoEmiBean().getNuAnn());
						response.setNuAnnExp(model.getDocumentoEmiBean().getNuAnnExp());
						response.setNuCorDoc(model.getDocumentoEmiBean().getNuCorDoc());
						response.setNuCorrExp(model.getDocumentoEmiBean().getNuCorrExp());
						response.setNuEmi(model.getDocumentoEmiBean().getNuEmi());
						response.setNuExpediente(model.getDocumentoEmiBean().getNuExpediente());
						response.setNuSecExp(model.getDocumentoEmiBean().getNuSecExp());

						response.setCode("0");
						response.setMessage("Se actualizó correctamente el trámite");
					} else {
						response.setCode("1");
						response.setMessage(data.getMessage());
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}

			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;

	}

	/*
	 * TRAMITE DOCUMENTO ANEXOS
	 */
	/*@POST
	@Path("/adjunto/anexo")
	@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PostMapping(path = {"/adjunto/anexo"}, produces = "application/json; charset=utf-8", consumes = "multipart/form-data")
	public InsertaAnexoBean insertarAnexo(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestPart MultipartFile archivo,
			@RequestParam("pNuAnn") String pNuAnn, @RequestParam("pNuEmi") String pNuEmi) {
		AnexoParametroBean model = new AnexoParametroBean();
		model.setpNuAnn(pNuAnn);
		model.setpNuEmi(pNuEmi);
		InsertaAnexoBean response = new InsertaAnexoBean();

		Mensaje resultado = null;
		try {

			resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					// VALIDAR REGLA DE NEGOCIO

					String vreturn = "NO_OK";
					byte[] bytes = archivo.getBytes();  // Obtiene los bytes del archivo directamente

					DocumentoFileBean fileMeta = new DocumentoFileBean();
					fileMeta.setNombreArchivo(archivo.getOriginalFilename());
					fileMeta.setTamanoArchivo((archivo.getSize() / 1024L) + " Kb");
					fileMeta.setTipoArchivo(archivo.getContentType());
					fileMeta.setArchivoBytes(bytes);
					vreturn = _docSgdInteService.insArchivoAnexo(user.getCOD_USER(), model.getpNuAnn(),
							model.getpNuEmi(), fileMeta);
					if (vreturn.equals("OK")) {
						String id = fileMeta.getIdDocumento();
						String nombreArchivo = fileMeta.getNombreArchivo();
						String tamanoArchivo = fileMeta.getTamanoArchivo();
						String tipoArchivo = fileMeta.getTipoArchivo();

						String res = "[{\"id\":\"%s\",\"name\":\"%s\",\"fileSize\":\"%s\",\"fileType\":\"%s\"}]";
						res = String.format(res, new Object[] { id, nombreArchivo, tamanoArchivo, tipoArchivo });
						// response.setMessage(res);
						response.setMessage("Se registró correctamente el anexo");
						response.setCode("0");
					} else {
						response.setCode(MjeValidacionProperties.ERROR_INESPERADO);
						response.setMessage("Error al procesar la solicitud");
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*@PUT
	@Path("/adjunto/anexo")
	@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping(path = {"/adjunto/anexo"}, produces = "application/json; charset=utf-8", consumes = "multipart/form-data")
	public ActualizaAnexoBean updateAnexo(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave, MultipartFile archivo,
			@RequestParam("pNuAnn") String pNuAnn, @RequestParam("pNuEmi") String pNuEmi,
			@RequestParam("pNuAne") String pNuAne) {
		ActualizaAnexoBean response = new ActualizaAnexoBean();
		AnexoParametroBean model = new AnexoParametroBean();
		model.setpNuAnn(pNuAnn);
		model.setpNuEmi(pNuEmi);
		model.setpNuAnn(pNuAnn);

		Mensaje resultado = null;
		try {
			resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);

			} else {
				String vreturn = "NO_OK";

				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {

					// VALIDAR REGLA DE NEGOCIO
					byte[] bytes = archivo.getBytes();  // Obtiene los bytes del archivo directamente

					DocumentoFileBean fileMeta = new DocumentoFileBean();
					fileMeta.setNombreArchivo(archivo.getOriginalFilename());
					fileMeta.setTamanoArchivo((archivo.getSize() / 1024L) + " Kb");
					fileMeta.setTipoArchivo(archivo.getContentType());
					fileMeta.setArchivoBytes(bytes);

					vreturn = _docSgdInteService.updArchivoAnexo(user.getCOD_USER(), model.getpNuAnn(),
							model.getpNuEmi(), model.getpNuAne(), fileMeta);
					if (vreturn.equals("OK")) {
						String id = fileMeta.getIdDocumento();
						String nombreArchivo = fileMeta.getNombreArchivo();
						String tamanoArchivo = fileMeta.getTamanoArchivo();
						String tipoArchivo = fileMeta.getTipoArchivo();

						String res = "[{\"id\":\"%s\",\"name\":\"%s\",\"fileSize\":\"%s\",\"fileType\":\"%s\"}]";
						res = String.format(res, new Object[] { id, nombreArchivo, tamanoArchivo, tipoArchivo });
						// response.setMessage(res);
						response.setMessage("Se actualizó correctamente el anexo");
						response.setCode("0");
					} else {
						response.setCode(MjeValidacionProperties.ERROR_INESPERADO);
						response.setMessage("Error al procesar la solicitud");
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;

	}

	/*
	 * TRAMITE DOCUMENTO PRINCIPAL
	 */
	/*@POST
	@Path("/adjunto/principal")
	@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PostMapping(path = {"/adjunto/principal"}, produces = "application/json; charset=utf-8", consumes = "multipart/form-data")
	public ActualizaAnexoBean InsertatDocumentoPrincipal(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestPart MultipartFile archivo,
			@RequestParam("pNuAnn") String pNuAnn, @RequestParam("pNuEmi") String pNuEmi) {
		ActualizaAnexoBean response = new ActualizaAnexoBean();
		AnexoParametroBean model = new AnexoParametroBean();
		model.setpNuAnn(pNuAnn);
		model.setpNuEmi(pNuEmi);

		Mensaje resultado = null;
		try {
			resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					String vreturn = "NO_OK";

					// 2.3 create new fileMeta
					InputStream upload = archivo.getInputStream();
					int nRead;
					byte[] data = new byte[1024 * 8];
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();


					while ((nRead = upload.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();
					byte[] bytes = buffer.toByteArray();


					DocumentoFileBean fileMeta = new DocumentoFileBean();
					fileMeta.setNombreArchivo(archivo.getOriginalFilename());
					fileMeta.setTamanoArchivo((archivo.getSize() / 1024L) + " Kb");
					fileMeta.setTipoArchivo(archivo.getContentType());
					fileMeta.setArchivoBytes(bytes);
					vreturn = _docSgdInteService.cargaDocumentoEmi(user.getCOD_USER(), pNuAnn, pNuEmi, fileMeta);
					if (vreturn.equals("OK")) {
						String id = fileMeta.getIdDocumento();
						String nombreArchivo = fileMeta.getNombreArchivo();
						String tamanoArchivo = fileMeta.getTamanoArchivo();
						String tipoArchivo = fileMeta.getTipoArchivo();

						String res = "[{\"id\":\"%s\",\"name\":\"%s\",\"fileSize\":\"%s\",\"fileType\":\"%s\"}]";
						res = String.format(res, id, nombreArchivo, tamanoArchivo, tipoArchivo);

						response.setMessage("Se registró correctamente el documento principal");
						response.setCode("0");
					} else {
						response.setCode(MjeValidacionProperties.ERROR_INESPERADO);
						response.setMessage("Error al procesar la solicitud");
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;

	}

	/*@PUT
	@Path("/adjunto/principal")
	@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping(path = {"/adjunto/principal"}, produces = "application/json; charset=utf-8", consumes = "multipart/form-data")
	public ActualizaAnexoBean UpdateDocumentoPrincipal(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestPart MultipartFile archivo,
			@RequestParam("pNuAnn") String pNuAnn, @RequestParam("pNuEmi") String pNuEmi,
			@RequestParam("pNuAne") String pNuAne) {
		ActualizaAnexoBean response = new ActualizaAnexoBean();
		AnexoParametroBean model = new AnexoParametroBean();
		model.setpNuAnn(pNuAnn);
		model.setpNuEmi(pNuEmi);

		Mensaje resultado = null;
		try {
			resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					String vreturn = "NO_OK";

					// 2.3 create new fileMeta
					InputStream upload = archivo.getInputStream();
					int nRead;
					byte[] data = new byte[1024 * 8];
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();


					while ((nRead = upload.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();
					byte[] bytes = buffer.toByteArray();


					DocumentoFileBean fileMeta = new DocumentoFileBean();
					fileMeta.setNombreArchivo(archivo.getOriginalFilename());
					fileMeta.setTamanoArchivo((archivo.getSize() / 1024L) + " Kb");
					fileMeta.setTipoArchivo(archivo.getContentType());
					fileMeta.setArchivoBytes(bytes);
					vreturn = _docSgdInteService.cargaDocumentoEmi(user.getCOD_USER(), pNuAnn, pNuEmi, fileMeta);
					if (vreturn.equals("OK")) {
						String id = fileMeta.getIdDocumento();
						String nombreArchivo = fileMeta.getNombreArchivo();
						String tamanoArchivo = fileMeta.getTamanoArchivo();
						String tipoArchivo = fileMeta.getTipoArchivo();

						String res = "[{\"id\":\"%s\",\"name\":\"%s\",\"fileSize\":\"%s\",\"fileType\":\"%s\"}]";
						res = String.format(res, id, nombreArchivo, tamanoArchivo, tipoArchivo);

						response.setMessage("Se actualizó correctamente el documento principal");
						response.setCode("0");
					} else {
						response.setCode(MjeValidacionProperties.ERROR_INESPERADO);
						response.setMessage("Error al procesar la solicitud");
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*
	 * PERSONA JURIDICA
	 */
	/*@POST
	@Path("/persona/juridica")
	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PostMapping(path = {"/persona/juridica"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseBean InsertarPersonaJuridica(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestBody ProveedorBean model) {
		ResponseBean response = new ResponseBean();
		try {
			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					// VALIDAR REGLA DE NEGOCIO
					MethodResponseBean<String> request = new MethodResponseBean<String>();
					request = _docSgdInteService.insProveedor(model);

					if (request.getSuccess()) {
						response.setCode("0");
						response.setMessage(request.getMessage());
					} else {
						response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
						response.setMessage(request.getMessage());
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*@PUT
	@Path("/persona/juridica")
	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping(path = {"/persona/juridica"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseBean UpdatePersonaJuridica(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestBody ProveedorBean model) {
		ResponseBean response = new ResponseBean();
		try {
			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					// VALIDAR REGLA DE NEGOCIO
					String respuesta = "NO_OK";
					respuesta = _docSgdInteService.updProveedor(model, user.getCOD_USER());

					if (respuesta.equals("OK")) {
						response.setCode("0");
						response.setMessage("Datos guardados");
					} else {
						response.setCode(MjeValidacionProperties.ERROR_INESPERADO);
						response.setMessage(respuesta);
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*
	 * PERSONA NATURAL
	 */
	@PostMapping(path = {"/persona/natural"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	//@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	//@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseBean InsertarPersonaNatural(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestBody CitizenBean ciudadano) {
		//System.out.println("ingreso a aass");
		ResponseBean response = new ResponseBean();
		try {
			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					MethodResponseBean<String> request = new MethodResponseBean<String>();
					request = _docSgdInteService.insCiudadano(ciudadano, user.getCOD_USER());

					if (request.getSuccess()) {
						response.setCode("0");
						response.setMessage(request.getMessage());
					} else {
						response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
						response.setMessage(request.getMessage());
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*@PUT
	@Path("/persona/natural")
	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping(path = {"/persona/natural"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseBean UpdatePersonaNatural(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestBody CitizenBean ciudadano) {
		ResponseBean response = new ResponseBean();
		try {
			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				String respuesta = "NO_OK";
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					respuesta = _docSgdInteService.updCiudadano(ciudadano, user.getCOD_USER());

					if (respuesta.equals("OK")) {
						response.setCode("0");
						response.setMessage("Datos guardados");
					} else {
						response.setCode("0");
						response.setMessage(respuesta);
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*
	 * OTROS ORIGENES
	 */
	/*@POST
	@Path("/persona/otroorigenes")
	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PostMapping(path = {"/persona/otroorigenes"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseBean InsertarOtrosOrigenes(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestBody OtroOrigenBean otroOrigen) {
		ResponseBean response = new ResponseBean();
		try {
			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					MethodResponseBean<String> request = new MethodResponseBean<String>();
					request = _docSgdInteService.insOtroOrigen(otroOrigen);

					if (request.getSuccess()) {
						response.setCode("0");
						response.setMessage(request.getMessage());
						response.setResult(request.getResult());
					} else {
						response.setCode("0");
						response.setMessage(request.getMessage());
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

	/*@PUT
	@Path("/persona/otroorigenes")
	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })*/
	@PutMapping(path = {"/persona/otroorigenes"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseBean UpdateOtrosOrigenes(@RequestHeader("usrautentication") String usrautentication,
			@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,@RequestBody OtroOrigenBean otroOrigen) {
		ResponseBean response = new ResponseBean();
		try {
			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);

			if (resultado != null) {
				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			} else {
				String respuesta = "NO_OK";
				Usuario user = usuarioService.ObtenerDatosUsuario(usrautentication);
				if (user != null) {
					respuesta = _docSgdInteService.updOtroOrigen(otroOrigen);

					if (respuesta.equals("OK")) {
						response.setCode("0");
						response.setMessage("Datos guardados");
					} else {
						response.setCode("0");
						response.setMessage(respuesta);
					}
				} else {
					response.setCode(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
					response.setMessage("Usuario session no existe");
				}
			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}
	/*
	 * TEST FILE
	 */
	/*
	 * @POST
	 * 
	 * @Path("/documento")
	 * 
	 * @Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE }) public
	 * MethodResponseBean<String> anexo(@HeaderParam("usuario") String
	 * usuario, @HeaderParam("clave") String clave, FormDataMultiPart file) {
	 * MethodResponseBean<String> response = new MethodResponseBean<String>();
	 * FormDataBodyPart stream = file.getField("file"); InputStream upload =
	 * stream.getValueAs(InputStream.class); // ByteArrayInputStream buffer = null;
	 * try { ByteArrayOutputStream buffer = new ByteArrayOutputStream(); int nRead;
	 * byte[] data = new byte[1024]; while ((nRead = upload.read(data, 0,
	 * data.length)) != -1) { buffer.write(data, 0, nRead); } buffer.flush(); byte[]
	 * byteArray = buffer.toByteArray();
	 * 
	 * System.out.println(byteArray); } catch (Exception e) {
	 * 
	 * } System.out.println(stream.getContentDisposition().getFileName());
	 * response.setCode("0"); response.setMessage("Test Document"); return response;
	 * }
	 */

	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	public static String getClientIpAddress() {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			for (String header : IP_HEADER_CANDIDATES) {
				String ip = request.getHeader(header);
				if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
					return ip;
				}
			}
			return request.getRemoteAddr();
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
}
