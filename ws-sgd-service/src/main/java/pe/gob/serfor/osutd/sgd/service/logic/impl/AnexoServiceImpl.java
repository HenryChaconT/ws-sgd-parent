package pe.gob.serfor.osutd.sgd.service.logic.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo2;
import pe.gob.serfor.osutd.sgd.repository.logic.AnexoDocumentoDao;
import pe.gob.serfor.osutd.sgd.service.logic.AnexoService;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;
//import pe.gob.serfor.wssisged.logic.dao.AnexoDocumentoDao;
//import pe.gob.serfor.wssisged.logic.model.Anexo2;
//import pe.gob.serfor.wssisged.logic.service.AnexoService;

@Component
@Transactional(readOnly = true)
public class AnexoServiceImpl implements AnexoService {

	@Autowired
	private AnexoDocumentoDao anexoDocumentoDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Anexo2 insArchivoAnexo(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo) {
		String vReturn = "NO_OK";
		String vnuAne = anexoDocumentoDao.getUltimoAnexo(pnuAnn, pnuEmi);

		DocumentoAnexoBean docAnexo = new DocumentoAnexoBean();

		docAnexo.setNuAnn(pnuAnn);
		docAnexo.setNuEmi(pnuEmi);
		docAnexo.setNuAne(vnuAne);
		docAnexo.setDeDet(pfileAnexo.getNombreArchivo());
		docAnexo.setDeRutOri(pfileAnexo.getNombreArchivo());
		docAnexo.setCoUseCre(coUsu);
		docAnexo.setCoUseMod(coUsu);
		validarBeanDocAnexo(docAnexo);

		InputStream archivoAnexo = new ByteArrayInputStream(pfileAnexo.getArchivoBytes());
		int size = (pfileAnexo.getArchivoBytes()).length;
		int maxUploadSize = 10000000;
		Anexo2 anexo = null;

		try {
			if (size <= maxUploadSize) {
				vReturn = anexoDocumentoDao.insArchivoAnexo(docAnexo, archivoAnexo, size);
				anexo = new Anexo2();
				anexo.setNuAne(vnuAne);
				anexo.setNuAnn(pnuAnn);
				anexo.setNuEmi(pnuEmi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (archivoAnexo != null) {
					archivoAnexo.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return anexo;
	}

	private void validarBeanDocAnexo(DocumentoAnexoBean docAnexo) {
		String pdeDet = docAnexo.getDeDet();
		if (pdeDet != null) {
			int tamCampoDeDet = 200;
			pdeDet = pdeDet.trim();
			int pLenDeDet = pdeDet.length();
			if (pLenDeDet > tamCampoDeDet) {
				pdeDet = pdeDet.substring(pLenDeDet - tamCampoDeDet, pLenDeDet);
			}
		}
		docAnexo.setDeDet(pdeDet);
	}

}
