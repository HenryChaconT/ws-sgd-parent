package pe.gob.serfor.osutd.sgd.repository.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.ArchivoPrincipal;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosSgdBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoInterno;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoMesaPartes;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoSgd;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoSgdMP;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.EnvioExterno;

//import pe.gob.serfor.wssisged.logic.bean.ArchivoPrincipal;
//import pe.gob.serfor.wssisged.logic.bean.ParametroDocumentoSgdBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosMPBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosSgdBean;
//import pe.gob.serfor.wssisged.logic.model.DocumentoInterno;
//import pe.gob.serfor.wssisged.logic.model.DocumentoMesaPartes;
//import pe.gob.serfor.wssisged.logic.model.DocumentoSgd;
//import pe.gob.serfor.wssisged.logic.model.DocumentoSgdMP;
//import pe.gob.serfor.wssisged.logic.model.EnvioExterno;

public interface DocSgdDao {

	ArchivoPrincipal buscarArchPrincipal(ParametrosBusqBean bean) throws Exception;

	List<DocumentoSgd> listarDocumento();

	List<DocumentoInterno> buscarDatosDoumentoInterno(ParametrosSgdBean bean) throws Exception;

	List<DocumentoMesaPartes> buscarDocumentoMesaPartes(ParametrosSgdBean bean) throws Exception;
	// public String buscarNroExpediente(ParametrosSgdBean bean) throws Exception;

	public List<DocumentoSgdMP> buscarDocumentosMesaPartes(ParametrosMPBean bean) throws Exception;

	public List<EnvioExterno> buscarTipoEnvioExterno(int anio, String numEmi) throws Exception;

	public List<DocumentoSgdMP> buscarDocumentosMesaPartesOtros(ParametrosMPBean bean) throws Exception;

	public List<DocumentoSgd> buscarDocumento(ParametrosSgdBean bean) throws Exception;

}
