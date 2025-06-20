package pe.gob.serfor.osutd.sgd.repository.logic;

import java.io.InputStream;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DestinatarioDocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoSolicitud;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ExpedienteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ReferenciaDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.RemitenteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DestinatarioDocumentoEmiBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocExternoRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoSolicitud;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ExpedienteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ReferenciaDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.RemitenteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.model.Usuario;

public interface DocSgdInteDao {
	/*
	 * GENERE NÚMERO DE EXPEDIENTE
	 */
	String getNumeroExpediente(ExpedienteDocExtRecepBean expedienteBean);

	String insExpedienteBean(ExpedienteDocExtRecepBean expedienteBean);

	String getNroCorrelativoDocumento(String pnuAnn, String pcoDepEmi, String ptiEmi);

	String insDocumentoExtBean(DocumentoExtRecepBean documentoExtRecepBean, ExpedienteDocExtRecepBean expedienteBean,
			RemitenteDocExtRecepBean remitenteDocExtRecepBean);

	String audiEstadoDocumentoRemito(Usuario currentUser, String nuAnn, String nuEmi, String esDoc);

	String insReferenciaDocumentoEmi(String pnuAnn, String pnuEmi, ReferenciaDocExtRecepBean ref);

	String insDestinatarioDocumentoEmi(String nuAnn, String nuEmi,
			DestinatarioDocumentoEmiBean destinatarioDocumentoEmiBean);

	String updRemitosResumenDes(String pnuAnn, String pnuEmi);

	String updRemitosResumenRef(String pnuAnn, String pnuEmi);

	/**/
	boolean isValidUpdateExpedienteBean(String pnuAnn, String pnuEmi);

	String updExpedienteBean(ExpedienteDocExtRecepBean expedienteBean);

	String updDocumentoExtBean(String nuAnn, String nuEmi, DocumentoExtRecepBean documentoExtRecepBean,
			ExpedienteDocExtRecepBean expedienteBean, RemitenteDocExtRecepBean remitenteDocExtRecepBean,
			String pcoUserMod);

	String updReferenciaDocumentoEmi(String pnuAnn, String pnuEmi, ReferenciaDocExtRecepBean ref);

	String delReferenciaDocumentoEmi(String pnuAnn, String pnuEmi, String pnuAnnRef, String pnuEmiRef);

	String updDestinatarioDocumentoEmi(String nuAnn, String nuEmi,
			DestinatarioDocumentoEmiBean destinatarioDocumentoEmiBean);

	String delDestinatarioDocumentoEmi(String nuAnn, String nuEmi, String pnuDes);

	String getDependenciaMesaPartes(String tipoMesa);

	DocumentoSolicitud existeDocumento(String pnuAnn, String nuSolicitud, String indOrigen);

	/**/

}
