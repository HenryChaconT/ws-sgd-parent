package pe.gob.serfor.osutd.sgd.rest.server;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.json.JSONException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ParametrosUsuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.UsuarioSgd;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UsuarioResponse;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MjeValidacionProperties;
import pe.gob.serfor.osutd.sgd.service.logic.UsuarioService;

//import pe.gob.serfor.wssisged.logic.bean.integracion.ParametrosUsuario;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UsuarioResponse;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.UsuarioSgd;
//import pe.gob.serfor.wssisged.logic.service.UsuarioService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.MjeValidacionProperties;

@RestController
@RequestMapping(value = "/sgd/usuario")
public class WsUsuario extends SpringBeanAutowiringSupport {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private UsuarioService usuarioService;

//	@POST
//	@Path("/login")
//	@Produces({ MediaType.APPLICATION_JSON_UTF8_VALUE })
	@PostMapping(path = {"/login"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public UsuarioResponse login(@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave,
								 @RequestBody ParametrosUsuario usuarioAcceso) throws IOException, JSONException {


		Mensaje resultado = null;
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		try {

			resultado = usuarioService.validarCredencialesSGD(usuarioAcceso.getUsuarioAcceso(), usuarioAcceso.getClaveAcceso(), ConstantesUtil.ID_APLICACION_SGD);
			//System.out.println(resultado.getCodigo());
			//System.out.println(resultado.getDesMensaje());
			if (resultado != null) {
				usuarioResponse.setMensaje(resultado);
				return usuarioResponse;
			}

			UsuarioSgd usuarioSGD = new UsuarioSgd();

			return usuarioService.getRptaIdentificacion(usuarioAcceso);

		} catch (Exception ex) {
			log.error("Error", ex);

			resultado = new Mensaje();
			resultado.setCodigo(MjeValidacionProperties.ERROR_INESPERADO_CODIGO);
			resultado.setDesMensaje(MjeValidacionProperties.ERROR_INESPERADO);
			usuarioResponse.setMensaje(resultado);

		}
		return usuarioResponse;
	}

}
