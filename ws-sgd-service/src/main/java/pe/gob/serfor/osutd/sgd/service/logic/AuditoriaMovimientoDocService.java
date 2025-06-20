package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;

//import pe.gob.serfor.wssisged.logic.model.Usuario;

public interface AuditoriaMovimientoDocService {
	String audiEstadoDocumentoRemito(Usuario currentUser,String nuAnn, String nuEmi, String esDoc);
	String audiEstadoDocumentoDestino(Usuario currentUser,String nuAnn, String nuEmi, String nuDes, String esDoc);
}
