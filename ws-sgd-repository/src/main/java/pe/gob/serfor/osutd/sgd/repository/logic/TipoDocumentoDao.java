package pe.gob.serfor.osutd.sgd.repository.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.TipoDocumento;

//import pe.gob.serfor.wssisged.logic.model.TipoDocumento;

public interface TipoDocumentoDao {

	public List<TipoDocumento> listarDocumentosMP() throws Exception;

}
