package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.AnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ArchivoPrincipalBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentoPrincipalBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosSgdBean;

//import pe.gob.serfor.wssisged.logic.bean.AnexoBean;
//
//import pe.gob.serfor.wssisged.logic.bean.ArchivoPrincipalBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentoPrincipalBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentosAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentosBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentosMPBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosMPBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosSgdBean;

public interface DocSgdService {

	public DocumentoPrincipalBean buscarDocumento(ParametrosSgdBean parametro) throws Exception;

	public DocumentoPrincipalBean buscarDocumentoBandeja(ParametrosSgdBean parametro) throws Exception;

	public DocumentosBean listarDocumento(ParametrosSgdBean parametro) throws Exception;

	public DocumentosAnexoBean listarAnexos(ParametrosBusqBean parametro) throws Exception;

	public AnexoBean descargarAnexo(ParametrosBusqBean parametro) throws Exception;

	public ArchivoPrincipalBean descargarArchivoPrincipal(ParametrosBusqBean parametro) throws Exception;

	public DocumentosMPBean listarDocumentosMesaPartes(ParametrosMPBean parametro) throws Exception;

	public AnexoBean descargarAnexo(ParametrosBusqAnexoBean parametro) throws Exception;

}
