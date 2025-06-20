package pe.gob.serfor.osutd.sgd.repository.acceso;

import org.springframework.dao.EmptyResultDataAccessException;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;

//import pe.gob.serfor.wssisged.logic.model.Usuario;

public abstract interface UsuarioDao {

	public abstract Usuario findUsuario(String username, String clave, Integer codAplicacion)
			throws EmptyResultDataAccessException, Exception;

	public abstract Usuario ObtenerDatosUsuario(String username) throws EmptyResultDataAccessException, Exception;

	public Usuario findUserByCo(String username) throws Exception;
}