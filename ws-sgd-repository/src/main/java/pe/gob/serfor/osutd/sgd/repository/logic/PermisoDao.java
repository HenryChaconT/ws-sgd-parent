package pe.gob.serfor.osutd.sgd.repository.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Permiso;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PermisoUuario;

//import pe.gob.serfor.wssisged.logic.model.Permiso;
//import pe.gob.serfor.wssisged.logic.model.PermisoUuario;

public interface PermisoDao {
	public boolean obtenerPermiso(Permiso permiso) throws Exception;

	public PermisoUuario tieneAcceso(String codAplicacion, String codUsuario) throws Exception;

}
