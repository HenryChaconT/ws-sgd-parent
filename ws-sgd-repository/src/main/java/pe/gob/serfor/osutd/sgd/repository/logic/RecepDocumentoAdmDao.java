package pe.gob.serfor.osutd.sgd.repository.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoBean;

public interface RecepDocumentoAdmDao {
	DocumentoBean getEstadoDocumento(String nuAnn, String nuEmi, String nuDes);
}
