package pe.gob.serfor.osutd.sgd.repository.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.AudiEstadosMovDocBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;

//import pe.gob.serfor.wssisged.logic.bean.integracion.AudiEstadosMovDocBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoObjBean;
//import pe.gob.serfor.wssisged.logic.model.Usuario;

public interface AuditoriaMovimientoDocDao {
	String audiVisualizaDocumento(DocumentoObjBean docVisualiza, Usuario usu);

	String audiEstadoDocumento(AudiEstadosMovDocBean audi);
}
