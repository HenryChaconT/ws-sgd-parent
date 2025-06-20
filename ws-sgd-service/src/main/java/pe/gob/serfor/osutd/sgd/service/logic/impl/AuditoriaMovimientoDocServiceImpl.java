package pe.gob.serfor.osutd.sgd.service.logic.impl;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.AudiEstadosMovDocBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.AuditoriaMovimientoDocDao;
import pe.gob.serfor.osutd.sgd.repository.logic.EmiDocumentoAdmDao;
import pe.gob.serfor.osutd.sgd.repository.logic.RecepDocumentoAdmDao;
import pe.gob.serfor.osutd.sgd.service.logic.AuditoriaMovimientoDocService;

//import pe.gob.serfor.wssisged.logic.bean.integracion.AudiEstadosMovDocBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoEmiBean;
//import pe.gob.serfor.wssisged.logic.dao.AuditoriaMovimientoDocDao;
//import pe.gob.serfor.wssisged.logic.dao.EmiDocumentoAdmDao;
//import pe.gob.serfor.wssisged.logic.dao.RecepDocumentoAdmDao;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.service.AuditoriaMovimientoDocService;

public class AuditoriaMovimientoDocServiceImpl implements AuditoriaMovimientoDocService {

	@Autowired
	private AuditoriaMovimientoDocDao _audiDao;
	@Autowired
	private RecepDocumentoAdmDao _receDoc;
	@Autowired
	private EmiDocumentoAdmDao _emiDoc;

	@Override
	public String audiEstadoDocumentoRemito(Usuario currentUser, String nuAnn, String nuEmi, String esDoc) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		AudiEstadosMovDocBean audi = new AudiEstadosMovDocBean();
		try {
			audi.setNuAnn(nuAnn);
			audi.setNuEmi(nuEmi);
			audi.setTiProceso("R");
			audi.setEsProceso(esDoc);
			audi.setDeUser(currentUser.getCoUsuario());
			audi.setDeIpPc(currentUser.getIpPC());
			audi.setDeNamePc(currentUser.getNombrePC());
			audi.setDeUserPc(currentUser.getUsuPc());
			if (isChangeEstadoDoc(audi)) {
				vReturn = this._audiDao.audiEstadoDocumento(audi);
			} else {
				vReturn = "OK";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vReturn;
	}

	@Override
	public String audiEstadoDocumentoDestino(Usuario currentUser, String nuAnn, String nuEmi, String nuDes,
			String esDoc) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		AudiEstadosMovDocBean audi = new AudiEstadosMovDocBean();
		try {
			audi.setNuAnn(nuAnn);
			audi.setNuEmi(nuEmi);
			audi.setNuDes(nuDes);
			audi.setTiProceso("D");
			audi.setEsProceso(esDoc);
			audi.setDeUser(currentUser.getCoUsuario());
			audi.setDeIpPc(currentUser.getIpPC());
			audi.setDeNamePc(currentUser.getNombrePC());
			audi.setDeUserPc(currentUser.getUsuPc());
			if (isChangeEstadoDoc(audi)) {
				vReturn = this._audiDao.audiEstadoDocumento(audi);
			} else {
				vReturn = "OK";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vReturn;
	}

	private boolean isChangeEstadoDoc(AudiEstadosMovDocBean audi) {
		boolean vResult = false;
		String esDocBD = null;
		try {
			if (audi.getNuAnn() != null && audi.getNuAnn().trim().length() > 0 && audi.getNuEmi() != null
					&& audi.getNuEmi().trim().length() > 0) {
				if (audi.getNuDes() != null && audi.getNuDes().trim().length() > 0) {
					DocumentoBean doc = this._receDoc.getEstadoDocumento(audi.getNuAnn(), audi.getNuEmi(),
							audi.getNuDes());
					esDocBD = doc.getEsDocRec();
				} else {
					DocumentoEmiBean doc = this._emiDoc.getEstadoDocumentoAudi(audi.getNuAnn(), audi.getNuEmi());
					esDocBD = doc.getEsDocEmi();
					if (esDocBD != null && esDocBD.equals("9") && audi.getEsProceso() != null
							&& audi.getEsProceso().equals("9")) {
						esDocBD = "X";
					}
				}
				if (audi.getEsProceso() != null && esDocBD != null && esDocBD.trim().length() > 0
						&& !audi.getEsProceso().equals(esDocBD)) {
					audi.setEsProceso(esDocBD);
					vResult = true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vResult;
	}

}
