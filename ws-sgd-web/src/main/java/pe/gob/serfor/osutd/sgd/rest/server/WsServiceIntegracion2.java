package pe.gob.serfor.osutd.sgd.rest.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
//import org.json.JSONException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.gob.serfor.osutd.sgd.service.logic.DocSgdInteService;
import pe.gob.serfor.osutd.sgd.service.logic.MesaPartesService;
import pe.gob.serfor.osutd.sgd.service.logic.UsuarioService;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ActualizaAnexoBean2;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.AdjuntarArchivoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.AnexoParametroBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CiudadanoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocExternoRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.InsertaAnexoBean2;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean2;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.PersonaJuridicaBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.response.DocumentoGeneradoModelBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.response.ResponseBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.response.TipoDocumentoMP;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo2;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ciudadano;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.OtroOrigen;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PersonaJuridica;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MjeValidacionProperties;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ActualizaAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ActualizaAnexoBean2;
//import pe.gob.serfor.wssisged.logic.bean.integracion.AdjuntarArchivoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.AnexoParametroBean;
//
//import pe.gob.serfor.wssisged.logic.bean.integracion.CiudadanoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocExternoRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.InsertaAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.InsertaAnexoBean2;
//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean2;
//import pe.gob.serfor.wssisged.logic.bean.integracion.PersonaJuridicaBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ProveedorBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.response.DocumentoGeneradoModelBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.response.ResponseBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.response.TipoDocumentoMP;
//import pe.gob.serfor.wssisged.logic.model.Anexo2;
//import pe.gob.serfor.wssisged.logic.model.Ciudadano;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.OtroOrigen;
//import pe.gob.serfor.wssisged.logic.model.PersonaJuridica;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.service.AnexoService;
//import pe.gob.serfor.wssisged.logic.service.DocSgdInteService;
//import pe.gob.serfor.wssisged.logic.service.MesaPartesService;
//import pe.gob.serfor.wssisged.logic.service.UsuarioService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;
//import pe.gob.serfor.wssisged.utils.MjeValidacionProperties;
//import pe.gob.serfor.wssisged.utils.Util;
import pe.gob.serfor.osutd.sgd.service.logic.AnexoService;

@RestController
@RequestMapping(value = "/sgd/integracion2/")
public class WsServiceIntegracion2 extends SpringBeanAutowiringSupport {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	MesaPartesService mesaPartesService;

	@Autowired
	DocSgdInteService _docSgdInteService;

	@Autowired
	AnexoService AnexoService;

//	@POST
//	@Path("/tramite")
//	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PostMapping(path = {"/tramite"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public DocumentoGeneradoModelBean insertarTramite(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ DocExternoRecepBean model) {
		DocumentoGeneradoModelBean response = new DocumentoGeneradoModelBean();
		try {
//			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));

//			if (resultado != null) {
//				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//			} else {

				String usrautentication = "admin";
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
					result = mesaPartesService.Add_DocumentoExternoRecep(model, userSession, /*idAplicacion*/ model.getIdAplicacion());
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
//			}
		} catch (Exception ex) {
			log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

//	@POST
//	@Path("/adjunto/anexo")
//	@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PostMapping(path = {"/adjunto/anexo"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public InsertaAnexoBean2 insertarAnexo(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ FormDataMultiPart archivo,
			@FormDataParam("pNuAnn") String pNuAnn, @FormDataParam("pNuEmi") String pNuEmi) {

		AnexoParametroBean model = new AnexoParametroBean();
		model.setpNuAnn(pNuAnn);
		model.setpNuEmi(pNuEmi);
		InsertaAnexoBean2 response1 = new InsertaAnexoBean2();

		Mensaje resultado = null;
		try {
//			resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));
//			if (resultado != null) {
//				Mensaje mensaje = new Mensaje();
//				mensaje.setCodigo(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				mensaje.setDesMensaje(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//				response1.setMensaje(mensaje);
//
//			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario("admin");
				if (user != null) {
					// VALIDAR REGLA DE NEGOCIO

					String vreturn = "NO_OK";
					FormDataBodyPart mpf = archivo.getField("archivo");
					InputStream upload = mpf.getValueAs(InputStream.class);
					int nRead;
					byte[] data = new byte[1024];
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					while ((nRead = upload.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();
					byte[] bytes = buffer.toByteArray();

					DocumentoFileBean fileMeta = new DocumentoFileBean();
					fileMeta.setNombreArchivo(mpf.getContentDisposition().getFileName());
					fileMeta.setTamanoArchivo((upload.available() / 1024L) + " Kb");
					fileMeta.setTipoArchivo(mpf.getMediaType().toString());

					fileMeta.setArchivoBytes(bytes);
					Anexo2 anexo = AnexoService.insArchivoAnexo(user.getCOD_USER(), model.getpNuAnn(),
							model.getpNuEmi(), fileMeta);
					if (anexo != null) {
						String id = fileMeta.getIdDocumento();
						String nombreArchivo = fileMeta.getNombreArchivo();
						String tamanoArchivo = fileMeta.getTamanoArchivo();
						String tipoArchivo = fileMeta.getTipoArchivo();

						String res = "[{\"id\":\"%s\",\"name\":\"%s\",\"fileSize\":\"%s\",\"fileType\":\"%s\"}]";
						res = String.format(res, new Object[] { id, nombreArchivo, tamanoArchivo, tipoArchivo });
						// response.setMessage(res);
						response1.setAnexo(anexo);
						Mensaje mensaje = new Mensaje();
						mensaje.setCodigo(ConstantesUtil.COD_ANEXO_REGISTRADO_SATISF);
						mensaje.setDesMensaje(ConstantesUtil.COD_ANEXO_REGISTRADO_SATISF_DESC);
						response1.setMensaje(mensaje);
					} else {

						Mensaje mensaje = new Mensaje();
						mensaje.setCodigo(MjeValidacionProperties.ERROR_INESPERADO);
						mensaje.setDesMensaje("Error al procesar la solicitud");
						response1.setMensaje(mensaje);
					}
				} else {

					Mensaje mensaje = new Mensaje();
					mensaje.setCodigo(ConstantesUtil.COD_USU_NO_EXISTE);
					mensaje.setDesMensaje(ConstantesUtil.COD_USU_NO_EXISTE_DESC);
					response1.setMensaje(mensaje);
				}
//			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			this.log.error("Error", ex);
			Mensaje mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_INESPERADO);
			mensaje.setDesMensaje("Error al procesar la solicitud");
			response1.setMensaje(mensaje);

		}
		return response1;
	}

//	@PUT
//	@Path("/adjunto/anexo")
//	@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PutMapping(path = {"/adjunto/anexo"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ActualizaAnexoBean2 updateAnexo(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ FormDataMultiPart archivo,
			@FormDataParam("pNuAnn") String pNuAnn, @FormDataParam("pNuEmi") String pNuEmi,
			@FormDataParam("pNuAne") String pNuAne) {
		ActualizaAnexoBean2 response = new ActualizaAnexoBean2();
		AnexoParametroBean model = new AnexoParametroBean();
		model.setpNuAnn(pNuAnn);
		model.setpNuEmi(pNuEmi);
		model.setpNuAnn(pNuAnn);

		Mensaje resultado = null;
		try {
//			resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));
//			if (resultado != null) {
//
//				Mensaje mensaje = new Mensaje();
//				mensaje.setCodigo(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				mensaje.setDesMensaje(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//				response.setMensaje(mensaje);
//
//			} else {
				String vreturn = "NO_OK";
				Usuario user = usuarioService.ObtenerDatosUsuario("admin");
				if (user != null) {
					// VALIDAR REGLA DE NEGOCIO
					FormDataBodyPart mpf = archivo.getField("archivo");
					InputStream upload = mpf.getValueAs(InputStream.class);
					int nRead;
					byte[] data = new byte[1024];
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					while ((nRead = upload.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();
					byte[] bytes = buffer.toByteArray();

					DocumentoFileBean fileMeta = new DocumentoFileBean();
					fileMeta.setNombreArchivo(mpf.getContentDisposition().getFileName());
					fileMeta.setTamanoArchivo((upload.available() / 1024L) + " Kb");
					fileMeta.setTipoArchivo(mpf.getMediaType().toString());
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

						Mensaje mensaje = new Mensaje();
						mensaje.setCodigo(ConstantesUtil.COD_ANEXO_ACTUALIZADO_SATISF);
						mensaje.setDesMensaje(ConstantesUtil.COD_ANEXO_ACTUALIZADO_SATISF_DESC);
						response.setMensaje(mensaje);

					} else {

						Mensaje mensaje = new Mensaje();
						mensaje.setCodigo(MjeValidacionProperties.ERROR_INESPERADO);
						mensaje.setDesMensaje("Error al procesar la solicitud");
						response.setMensaje(mensaje);
					}
				} else {
					Mensaje mensaje = new Mensaje();
					mensaje.setCodigo(ConstantesUtil.COD_USU_NO_EXISTE);
					mensaje.setDesMensaje(ConstantesUtil.COD_USU_NO_EXISTE_DESC);
					response.setMensaje(mensaje);
				}
//			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			this.log.error("Error", ex);

			Mensaje mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			response.setMensaje(mensaje);
		}
		return response;

	}

	/*
	 * TRAMITE DOCUMENTO PRINCIPAL
	 */
//	@POST
//	@Path("/adjunto/principal")
//	@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PostMapping(path = {"/adjunto/principal"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public AdjuntarArchivoBean InsertatDocumentoPrincipal(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ FormDataMultiPart archivo,
			@FormDataParam("pNuAnn") String pNuAnn, @FormDataParam("pNuEmi") String pNuEmi) {
		AdjuntarArchivoBean response = new AdjuntarArchivoBean();
		AnexoParametroBean model = new AnexoParametroBean();
		model.setpNuAnn(pNuAnn);
		model.setpNuEmi(pNuEmi);

		Mensaje resultado = null;
		try {
//			resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));
//			if (resultado != null) {
//				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario("admin");
				if (user != null) {
					String vreturn = "NO_OK";

					// 2.3 create new fileMeta
					FormDataBodyPart mpf = archivo.getField("archivo");
					InputStream upload = mpf.getValueAs(InputStream.class);
					int nRead;
					byte[] data = new byte[1024 * 8];
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					while ((nRead = upload.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();
					byte[] bytes = buffer.toByteArray();

					DocumentoFileBean fileMeta = new DocumentoFileBean();
					fileMeta.setNombreArchivo(mpf.getContentDisposition().getFileName());
					fileMeta.setTamanoArchivo((upload.available() / 1024L) + " Kb");
					fileMeta.setTipoArchivo(mpf.getMediaType().toString());
					fileMeta.setArchivoBytes(bytes);

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
//			}
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
//	@POST
//	@Path("/personajuridica")
//	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PostMapping(path = {"/personajuridica"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public PersonaJuridicaBean InsertarPersonaJuridica(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ PersonaJuridica model) {
		PersonaJuridicaBean response = new PersonaJuridicaBean();
		try {
//			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));
//
//			if (resultado != null) {
//				Mensaje mensaje = new Mensaje();
//				mensaje.setCodigo(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				mensaje.setDesMensaje(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//				response.setMensaje(mensaje);
//
//			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario("admin");
				if (user != null) {
					response = mesaPartesService.registrarPersonaJuridica(model, user.getCOD_USER());

				} else {
					Mensaje mensaje = new Mensaje();
					mensaje.setCodigo(ConstantesUtil.COD_USUARIO_NO_EXISTE);
					mensaje.setDesMensaje(ConstantesUtil.COD_USUARIO_NO_EXISTE_DESC);
					response.setMensaje(mensaje);
				}
//			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			Mensaje mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			response.setMensaje(mensaje);
		}
		return response;
	}

	/*
	 * PERSONA NATURAL
	 */
//	@POST
//	@Path("/persona/ciudadano")
//	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PostMapping(path = {"/persona/ciudadano"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public CiudadanoBean InsertarPersonaNatural(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ Ciudadano ciudadano) {
		CiudadanoBean response = new CiudadanoBean();
		try {
//			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));
//
//			if (resultado != null) {
//				response.setMensaje(new Mensaje());
//				response.getMensaje().setCodigo(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				response.getMensaje().setDesMensaje(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//
//			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario("admin");
				if (user != null) {

					response = mesaPartesService.registrarCiudadano(ciudadano, user.getCOD_USER());

				}
//			}
		} catch (Exception ex) {
			this.log.error("Error", ex);

			response.setMensaje(new Mensaje());
			response.getMensaje().setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.getMensaje().setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);

		}
		return response;
	}

	/*
	 * OTROS ORIGENES
	 */
//	@POST
//	@Path("/persona/otroorigenes")
//	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PostMapping(path = {"/persona/otroorigenes"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public OtroOrigenBean2 registrarOtrosOrigenes(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ OtroOrigen otroOrigen) {
		OtroOrigenBean2 response = new OtroOrigenBean2();
		try {
//			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));
//
//			if (resultado != null) {
//
//				response.setMensaje(new Mensaje());
//				response.getMensaje().setCodigo(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				response.getMensaje().setDesMensaje(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//			} else {
				Usuario user = usuarioService.ObtenerDatosUsuario("admin");
				if (user != null) {

					response = mesaPartesService.registrarOtroOrigen(otroOrigen);
					response.setOtroOrigen(otroOrigen);

				} else {
					Mensaje mensaje = new Mensaje();
					mensaje.setCodigo(ConstantesUtil.COD_USUARIO_NO_EXISTE);
					mensaje.setDesMensaje(ConstantesUtil.COD_USUARIO_NO_EXISTE_DESC);
					response.setMensaje(mensaje);
				}
//			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setMensaje(new Mensaje());
			response.getMensaje().setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.getMensaje().setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

//	@PUT
//	@Path("/persona/otroorigenes")
//	@Consumes({ MediaType.APPLICATION_JSON_UTF8_VALUE })
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PutMapping(path = {"/persona/otroorigenes"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseBean UpdateOtrosOrigenes(/*@HeaderParam("idAplicacion") String idAplicacion,
			@HeaderParam("usuario") String usuario, @HeaderParam("clave") String clave,*/ OtroOrigenBean otroOrigen) {
		ResponseBean response = new ResponseBean();
		try {
//			Mensaje resultado = usuarioService.validarCredencialesSGD(usuario, clave, Integer.parseInt(idAplicacion));
//
//			if (resultado != null) {
//				response.setCode(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
//				response.setMessage(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
//			} else {
				String respuesta = "NO_OK";
				Usuario user = usuarioService.ObtenerDatosUsuario("admin");
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
//			}
		} catch (Exception ex) {
			this.log.error("Error", ex);
			response.setCode(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			response.setMessage(MjeValidacionProperties.ERROR_INESPERADO);
		}
		return response;
	}

//	@GET
//	@Path("/listarTipoDocumentosMP")
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@GetMapping(path = {"/listarTipoDocumentosMP"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public TipoDocumentoMP listarTipoDocumentosMP(/*@HeaderParam("usuario") String usuario,
			@HeaderParam("clave") String clave, @HeaderParam("idAplicacion") String idAplicacion*/)
			throws IOException, JSONException {

		Mensaje resultado = null;
		TipoDocumentoMP tipoDocumento = new TipoDocumentoMP();
		try {
//			if (idAplicacion != null && !idAplicacion.trim().equals(""))
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave,
//						Integer.parseInt(idAplicacion.trim()));
//			else {
//				resultado = usuarioService.validarCredencialesSGD(usuario, clave, ConstantesUtil.ID_APLICACION_SGD);
//			}
//
//			if (resultado != null) {
//				tipoDocumento.setMensaje(resultado);
//				return tipoDocumento;
//			}

			tipoDocumento = mesaPartesService.listarDocumentosMP();
			resultado = new Mensaje();
			if (tipoDocumento.getLstDocumentos() != null && !tipoDocumento.getLstDocumentos().isEmpty()) {

				resultado = new Mensaje();
				resultado.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
				resultado.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);

				tipoDocumento.setMensaje(resultado);

			} else {
				resultado = new Mensaje();
				resultado.setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
				resultado.setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
				tipoDocumento.setMensaje(resultado);
			}

		} catch (Exception ex) {
			log.error("Error", ex);

			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			tipoDocumento.setMensaje(resultado);
			return tipoDocumento;
		}
		return tipoDocumento;
	}
	
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
