package pe.gob.serfor.osutd.sgd.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.logic.EmiDocumentoAdmDao;
import pe.gob.serfor.osutd.sgd.service.logic.EmiDocumentoAltaService;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoObjBean;
//import pe.gob.serfor.wssisged.logic.dao.EmiDocumentoAdmDao;
//import pe.gob.serfor.wssisged.logic.service.EmiDocumentoAltaService;

@Component
@Transactional(readOnly = true)
public class EmiDocumentoAltaServiceImpl implements EmiDocumentoAltaService {

	@Autowired
	private EmiDocumentoAdmDao _EmiDocumentoAdmDao;

	@Override
	public String cargaDocumentoEmi(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo)
			throws Exception {
		String vReturn = "NO_OK";
		DocumentoObjBean docObjBean = new DocumentoObjBean();

		docObjBean.setNuAnn(pnuAnn);
		docObjBean.setNuEmi(pnuEmi);
		docObjBean.setTiCap("01");
		docObjBean.setNombreArchivo(pfileAnexo.getNombreArchivo());
		docObjBean.setCoUseMod(coUsu);
		docObjBean.setTipoDoc(
				docObjBean.getNombreArchivo().substring(docObjBean.getNombreArchivo().lastIndexOf('.') + 1));

		docObjBean.setNombreArchivo(
				docObjBean.getNombreArchivo().substring(docObjBean.getNombreArchivo().lastIndexOf('\\') + 1));

		byte[] archivoByte = pfileAnexo.getArchivoBytes();
		docObjBean.setDocumento(archivoByte);
		docObjBean.setNuTamano((int) Math.round(archivoByte.length / 1024.0D));

		vReturn = _EmiDocumentoAdmDao.updDocumentoObj(docObjBean);

		return vReturn;
	}

}
