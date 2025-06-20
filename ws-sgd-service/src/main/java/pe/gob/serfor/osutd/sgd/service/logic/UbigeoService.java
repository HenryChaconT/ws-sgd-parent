package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDepartamento;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDistrito;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroProvincia;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UbigeoResponse;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UbigeoResponseProvincia;

//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDepartamento;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDistrito;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroProvincia;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UbigeoResponse;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UbigeoResponseProvincia;

public interface UbigeoService {

	public UbigeoResponse buscarDepartamento(ParametroDepartamento parametro) throws Exception;;

	public UbigeoResponseProvincia buscarProvincia(ParametroProvincia parametro) throws Exception;

	public UbigeoResponse buscarDistrito(ParametroDistrito parametro) throws Exception;

}
