package pe.gob.serfor.osutd.sgd.repository.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CitizenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ciudadano;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.CitizenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.CiudadanoBean;
//import pe.gob.serfor.wssisged.logic.model.Ciudadano;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

public interface CiudadanoDao {

	MethodResponseBean<String> insCiudadano(CitizenBean ciudadano, String codUsuario);

	Boolean CiudadanoExiste(String NuLem);

	String updCiudadano(CitizenBean ciudadano, String codUsuario);

	void insertar(Ciudadano ciudadano, String codUsuario) throws Exception;

	void actualizar(Ciudadano ciudadano, String codUsuario) throws Exception;
}
