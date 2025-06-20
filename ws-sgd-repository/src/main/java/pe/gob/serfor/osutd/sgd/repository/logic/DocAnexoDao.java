package pe.gob.serfor.osutd.sgd.repository.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo;

//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqBean;
//import pe.gob.serfor.wssisged.logic.model.Anexo;

public interface DocAnexoDao {
	List<Anexo> listarAnexos(String anio, String nuEmi) throws Exception;

	public Anexo buscarAnexo(ParametrosBusqBean bean) throws Exception;

}
