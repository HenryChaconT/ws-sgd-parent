package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ParametrosUsuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UsuarioResponse;

//import pe.gob.serfor.wssisged.logic.bean.integracion.ParametrosUsuario;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UsuarioResponse;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.model.UsuarioSgd;

public abstract interface UsuarioService {

	public String validarCredenciales(String usuario, String clave, Integer codAplicacion);

	public Mensaje validarCredencialesSGD(String usuario, String clave, Integer codAplicacion) throws Exception;

	public UsuarioResponse getRptaIdentificacion(ParametrosUsuario usuarioAcceso) throws Exception;

	public Usuario ObtenerDatosUsuario(String usuario);

	public Usuario findUserByCo(String username) throws Exception;
}