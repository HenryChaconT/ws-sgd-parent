package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo2;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;
//import pe.gob.serfor.wssisged.logic.model.Anexo2;

public interface AnexoService {

	Anexo2 insArchivoAnexo(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo);

}
