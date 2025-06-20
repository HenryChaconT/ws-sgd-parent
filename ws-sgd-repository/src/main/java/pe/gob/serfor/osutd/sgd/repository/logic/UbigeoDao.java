package pe.gob.serfor.osutd.sgd.repository.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ubigeo;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDepartamento;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDistrito;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroProvincia;

//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDepartamento;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDistrito;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroProvincia;
//import pe.gob.serfor.wssisged.logic.model.Ubigeo;

public interface UbigeoDao {

	public List<Ubigeo> buscarDepartamento(ParametroDepartamento parametro) throws Exception;

	public List<Ubigeo> buscarProvincia(ParametroProvincia parametro) throws Exception;

	public List<Ubigeo> buscarDistrito(ParametroDistrito parametro) throws Exception;

}
