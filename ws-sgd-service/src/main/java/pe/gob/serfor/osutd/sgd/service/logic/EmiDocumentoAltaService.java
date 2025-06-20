package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;

public interface EmiDocumentoAltaService {
	
	
	String cargaDocumentoEmi(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo) throws Exception;
}
