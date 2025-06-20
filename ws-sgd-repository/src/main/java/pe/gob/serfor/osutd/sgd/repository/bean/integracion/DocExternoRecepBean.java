package pe.gob.serfor.osutd.sgd.repository.bean.integracion;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DocExternoRecepBean {

	private String nuAnn;
	private String nuEmi;
	private String nuAnnExp;
	private String nuSecExp;
	//identicar al usaurio
	private String coDependencia;// cod osud*/
	private String cempCodEmp; // cod osus
	private String coUserMod; //cod 0sud rroman
	
	private String accionBD; // ins
	private String idAplicacion;
	private String idSolicitud;
	
	private DocumentoExtRecepBean documentoEmiBean;
	private ExpedienteDocExtRecepBean expedienteEmiBean;
	private RemitenteDocExtRecepBean remitenteEmiBean;
	private ArrayList<ReferenciaDocExtRecepBean> lstReferencia;
	private ArrayList<DestinatarioDocumentoEmiBean> lstDestinatario;
	
	public String getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public String getNuAnn() {
		return nuAnn;
	}

	public void setNuAnn(String nuAnn) {
		this.nuAnn = nuAnn;
	}

	public String getNuEmi() {
		return nuEmi;
	}

	public void setNuEmi(String nuEmi) {
		this.nuEmi = nuEmi;
	}

	public String getNuAnnExp() {
		return nuAnnExp;
	}

	public void setNuAnnExp(String nuAnnExp) {
		this.nuAnnExp = nuAnnExp;
	}

	public String getNuSecExp() {
		return nuSecExp;
	}

	public void setNuSecExp(String nuSecExp) {
		this.nuSecExp = nuSecExp;
	}

	public String getCoUserMod() {
		return coUserMod;
	}

	public void setCoUserMod(String coUserMod) {
		this.coUserMod = coUserMod;
	}

	public String getCempCodEmp() {
		return cempCodEmp;
	}

	public void setCempCodEmp(String cempCodEmp) {
		this.cempCodEmp = cempCodEmp;
	}

	public String getCoDependencia() {
		return coDependencia;
	}

	public void setCoDependencia(String coDependencia) {
		this.coDependencia = coDependencia;
	}

	public String getAccionBD() {
		return accionBD;
	}

	public void setAccionBD(String accionBD) {
		this.accionBD = accionBD;
	}

	public DocumentoExtRecepBean getDocumentoEmiBean() {
		return documentoEmiBean;
	}

	public void setDocumentoEmiBean(DocumentoExtRecepBean documentoEmiBean) {
		this.documentoEmiBean = documentoEmiBean;
	}

	public ExpedienteDocExtRecepBean getExpedienteEmiBean() {
		return expedienteEmiBean;
	}

	public void setExpedienteEmiBean(ExpedienteDocExtRecepBean expedienteEmiBean) {
		this.expedienteEmiBean = expedienteEmiBean;
	}

	public RemitenteDocExtRecepBean getRemitenteEmiBean() {
		return remitenteEmiBean;
	}

	public void setRemitenteEmiBean(RemitenteDocExtRecepBean remitenteEmiBean) {
		this.remitenteEmiBean = remitenteEmiBean;
	}

	public ArrayList<ReferenciaDocExtRecepBean> getLstReferencia() {
		return lstReferencia;
	}

	public void setLstReferencia(ArrayList<ReferenciaDocExtRecepBean> lstReferencia) {
		this.lstReferencia = lstReferencia;
	}

	public ArrayList<DestinatarioDocumentoEmiBean> getLstDestinatario() {
		return lstDestinatario;
	}

	public void setLstDestinatario(ArrayList<DestinatarioDocumentoEmiBean> lstDestinatario) {
		this.lstDestinatario = lstDestinatario;
	}
	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
}
