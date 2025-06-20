package pe.gob.serfor.osutd.sgd.service.logic.impl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
//import pe.gob.serfor.wssisged.logic.bean.DocumentoBean;
//import pe.gob.serfor.wssisged.logic.bean.UsuarioAcceso;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ParametrosUsuario;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UsuarioResponse;
//import pe.gob.serfor.wssisged.logic.dao.EmpleadoDao;
//import pe.gob.serfor.wssisged.logic.dao.PermisoDao;
//import pe.gob.serfor.wssisged.logic.dao.UsuarioDao;
//import pe.gob.serfor.wssisged.logic.dao.UsuarioSgdDao;
//import pe.gob.serfor.wssisged.logic.model.Empleado;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.PermisoUuario;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.model.UsuarioSgd;
//import pe.gob.serfor.wssisged.logic.service.UsuarioService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.MjeValidacionProperties;
//import pe.gob.serfor.wssisged.utils.Util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.serfor.osutd.sgd.repository.acceso.UsuarioDao;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ParametrosUsuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.UsuarioAcceso;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Empleado;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PermisoUuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.UsuarioSgd;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UsuarioResponse;
import pe.gob.serfor.osutd.sgd.repository.logic.EmpleadoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.PermisoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.UsuarioSgdDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MjeValidacionProperties;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Util;
import pe.gob.serfor.osutd.sgd.service.logic.UsuarioService;

@Component
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;
	@Autowired
	UsuarioSgdDao usuarioSgdDao;
	@Autowired
	PermisoDao permisoDao;
	@Autowired
	EmpleadoDao empleadoDao;

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public String validarCredenciales(String usuario, String clave, Integer codAplicacion) {
		ObjectMapper mapper = new ObjectMapper();

		DocumentoBean documento = new DocumentoBean();
		Mensaje mensaje;
		String jsonInString = "";

		Usuario objUsuario;
		try {

			if (Util.esVacio(usuario)) {
				mensaje = new Mensaje();
				mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
				mensaje.setDesMensaje(MjeValidacionProperties.ERROR_SEGURIDIAD_CAMPO_USUARIO_VACIO);
				documento.setMensaje(mensaje);
				jsonInString = mapper.writeValueAsString(documento);
				return jsonInString;
			} else if (Util.esVacio(clave)) {
				mensaje = new Mensaje();
				mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
				mensaje.setDesMensaje(MjeValidacionProperties.ERROR_SEGURIDAD_CAMPO_CLAVE_VACIO);
				documento.setMensaje(mensaje);
				jsonInString = mapper.writeValueAsString(documento);
				return jsonInString;
			}
			objUsuario = usuarioDao.findUsuario(usuario, clave, codAplicacion);
			if (objUsuario == null) {
				mensaje = new Mensaje();
				mensaje.setCodigo(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
				mensaje.setDesMensaje(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
				documento.setMensaje(mensaje);
				jsonInString = mapper.writeValueAsString(documento);
				return jsonInString;

			}
			return jsonInString;
		} catch (Exception e) {
			log.error("Error en consulta ususario", e);
		}

		try {
			mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			documento.setMensaje(mensaje);
			jsonInString = mapper.writeValueAsString(documento);
		} catch (JsonGenerationException e) {
			log.error("Error en consulta ususario", e);
		} catch (JsonMappingException e) {
			log.error("Error en consulta ususario", e);
		} catch (IOException e) {
			log.error("Error en consulta ususario", e);
		}
		return jsonInString;
	}

	@Override
	public Mensaje validarCredencialesSGD(String usuario, String clave, Integer codAplicacion) throws Exception {

		Mensaje mensaje;

		Usuario objUsuario;

		if (Util.esVacio(usuario)) {
			mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_SEGURIDIAD_CAMPO_USUARIO_VACIO);
			return mensaje;
		} else if (Util.esVacio(clave)) {
			mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_SEGURIDAD_CAMPO_CLAVE_VACIO);
			return mensaje;
		}
		objUsuario = usuarioDao.findUsuario(usuario, clave, codAplicacion);
		if (objUsuario == null) {
			mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_NOAUTORIZADO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_NOAUTORIZADO_DESCRIPCION);
			return mensaje;
		}
		return null;

	}

	@Override
	public UsuarioResponse getRptaIdentificacion(ParametrosUsuario usuarioAcceso) throws Exception {
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		Mensaje mensaje = null;

		if (Util.esVacio(usuarioAcceso.getUsuarioAcceso())) {
			mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_SEGURIDIAD_CAMPO_USUARIO_VACIO);
			usuarioResponse.setMensaje(mensaje);
			return usuarioResponse;
		} else if (Util.esVacio(usuarioAcceso.getClaveAcceso())) {
			mensaje = new Mensaje();
			mensaje.setCodigo(MjeValidacionProperties.ERROR_CAMPO_INGRESADO_VACIO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.ERROR_SEGURIDAD_CAMPO_CLAVE_VACIO);
			usuarioResponse.setMensaje(mensaje);
			return usuarioResponse;
		}
		mensaje = new Mensaje();
		UsuarioSgd usuario = new UsuarioSgd();
		usuario.setCoUsuario(usuarioAcceso.getUsuarioAcceso());
		usuario.setDePassword(usuarioAcceso.getClaveAcceso());
		usuarioSgdDao.getRptaIdentificacion(mensaje, usuario);
		if (mensaje.getCodigo().equals("00")) {
			PermisoUuario permisoUuario = permisoDao
					.tieneAcceso(String.valueOf(ConstantesUtil.ID_APLICACION_SGD), usuario.getCoUsuario());
			if (permisoUuario == null) {
				mensaje.setCodigo(ConstantesUtil.COD_NO_AUTORIZADO);
				mensaje.setDesMensaje(ConstantesUtil.COD_NO_AUTORIZADO_DESCRIPCION);
				usuarioResponse.setMensaje(mensaje);
				return usuarioResponse;
			}
			UsuarioAcceso usuarioacceso = new UsuarioAcceso();
			usuarioacceso.setEsAdmin(permisoUuario.getEsAdmin());// 1 ADMIN 0 NO ADMIN
			Empleado empleado = empleadoDao.buscarEmpleado(usuario.getCempCodemp());
			if (empleado != null) {
				usuarioacceso.setDeCargo(empleado.getDeCargo());

			}

			usuarioacceso.setCoCargo(empleado.getCoCargo());
			usuarioacceso.setCoEmpleado(usuario.getCempCodemp());
			usuarioacceso.setCodUser(usuarioAcceso.getUsuarioAcceso());

			usuarioacceso
					.setNombres(empleado.getNombres() + " " + empleado.getApPaterno() + " " + empleado.getApPaterno());
			usuarioResponse.setMensaje(mensaje);

			mensaje.setCodigo(MjeValidacionProperties.AUTORIZADO_CODIGO);
			mensaje.setDesMensaje(MjeValidacionProperties.AUTORIZADO_DESCRIPCION);
			usuarioResponse.setUsuario(usuarioacceso);

		} else {
			usuarioResponse.setMensaje(mensaje);

		}

		return usuarioResponse;

	}

	@Override
	public Usuario ObtenerDatosUsuario(String usuario) {
		// TODO Auto-generated method stub
		Usuario objUsuario = null;
		try {
			objUsuario = usuarioSgdDao.ObtenerDatosUsuario(usuario);
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objUsuario;
	}

	@Override
	public Usuario findUserByCo(String couser) throws Exception {
		// TODO Auto-generated method stub
		Usuario objUsuario = null;
		try {
			objUsuario = usuarioDao.findUserByCo(couser);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objUsuario;
	}

}