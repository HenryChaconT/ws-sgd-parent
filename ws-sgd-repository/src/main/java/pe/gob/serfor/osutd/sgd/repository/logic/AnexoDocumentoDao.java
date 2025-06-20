package pe.gob.serfor.osutd.sgd.repository.logic;

import java.io.InputStream;
import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoAnexoBean;
//import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentoAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo;

//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoAnexoBean;
//import pe.gob.serfor.wssisged.logic.model.Anexo;

public interface AnexoDocumentoDao {

	String insArchivoAnexo(DocumentoAnexoBean docAnexo, InputStream archivoAnexo, int size);

	String updArchivoAnexo(DocumentoAnexoBean docAnexo, InputStream archivoAnexo, int size);

	String getUltimoAnexo(String pnuAnn, String pnuEmi);

	List<Anexo> listarAnexos(String anio, String nuEmi) throws Exception;

	public Anexo buscarAnexo(ParametrosBusqAnexoBean bean) throws Exception;

}
