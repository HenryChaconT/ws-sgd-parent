package pe.gob.serfor.osutd.sgd.repository.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;

//import pe.gob.serfor.wssisged.logic.model.Usuario;

public interface UsuarioSgdIntDao {
	Usuario ObtenerDatosUsuario(String username);
}
