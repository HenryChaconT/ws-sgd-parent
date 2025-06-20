package pe.gob.serfor.osutd.sgd.repository.logic;

import org.springframework.dao.EmptyResultDataAccessException;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.UsuarioSgd;

//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.model.UsuarioSgd;

public abstract interface UsuarioSgdDao {

	public abstract Usuario ObtenerDatosUsuario(String username) throws EmptyResultDataAccessException, Exception;

	public void getRptaIdentificacion(Mensaje msg, UsuarioSgd usuario) throws Exception;

	public void desencripta(Mensaje msg, UsuarioSgd usuario);

}