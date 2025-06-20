package pe.gob.serfor.osutd.sgd.repository.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.OtroOrigen;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean;
//import pe.gob.serfor.wssisged.logic.model.OtroOrigen;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

public interface OtroOrigenDao {
	MethodResponseBean<String> insOtroOrigen(OtroOrigenBean otroOrigen);

	Boolean OtroOrigenExiste(String NuDocOtrOri);

	String updOtroOrigen(OtroOrigenBean otroOrigen);

	public String OtroOrigenExisteNumero(String tipoDoc, String nuDocOtr) throws Exception;

	public String OtroOrigenExisteCodigo(String coOtroOri) throws Exception;

	public void actualizar(OtroOrigen otroOrigen) throws Exception;

	public void insertar(OtroOrigen otroOrigen) throws Exception;
}
