package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

import java.util.Date;

public class DocumentoSgdMP {
	

	

	private String nuEmi;
	private String anio;
	private String nuCorEmi;
	private String coGru;
	private Date fecEmision;
	private String deTiEmi;
	
	
	private String desDocumento;
	private String numDoc;
	private String esDocEmi;//ES_DOC_EMI;
	private String numExpediente;
	private String  estadoDocDes;//ESTADO_DOC_DES
	private String dependenciaOrigen;
	private String remitente;
	private String bandejaorigen;
	private String asunto;
	private String coDepEmi; 
	private String coEmpEmi;
	private String coOtrOriEmi;
	private String nuDocEmi;
	private String deDocSig;
	private String tiEmi;
	private String nuRucEmi;
	
	private String numFolios;
	private String nuDniCiudadano;
	private String nombreCiudadano;
	private String nuDniRepLegal; //NU_DNI_REP_LEG
	private String nombreRepLegal;
	private String codTipDoc;
	private String nuDocOtrOri;//o.NU_DOC_OTR_ORI
	 
	 
	public String getNuDocOtrOri() {
		return nuDocOtrOri;
	}
	public void setNuDocOtrOri(String nuDocOtrOri) {
		this.nuDocOtrOri = nuDocOtrOri;
	}
	// cadena.append("A.NU_DNI_EMI,   IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL(A.NU_DNI_EMI) NOMBRE_CIUDADANO ");
	 //cadena.append("A.REMI_NU_DNI_EMI	NU_DNI_REP_LEG,   IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL(A.REMI_NU_DNI_EMI) NOMBRE_REP_LEG");
	public String getNuDniCiudadano() {
		return nuDniCiudadano;
	}
	public void setNuDniCiudadano(String nuDniCiudadano) {
		this.nuDniCiudadano = nuDniCiudadano;
	}
	public String getNombreCiudadano() {
		return nombreCiudadano;
	}
	public void setNombreCiudadano(String nombreCiudadano) {
		this.nombreCiudadano = nombreCiudadano;
	}
	public String getNuDniRepLegal() {
		return nuDniRepLegal;
	}
	public void setNuDniRepLegal(String nuDniRepLegal) {
		this.nuDniRepLegal = nuDniRepLegal;
	}
	public String getNombreRepLegal() {
		return nombreRepLegal;
	}
	public void setNombreRepLegal(String nombreRepLegal) {
		this.nombreRepLegal = nombreRepLegal;
	}
	
	
	public String getNumFolios() {
		return numFolios;
	}
	public void setNumFolios(String numFolios) {
		this.numFolios = numFolios;
	}
	public String getBandejaorigen() {
		return bandejaorigen;
	}
	public void setBandejaorigen(String bandejaorigen) {
		this.bandejaorigen = bandejaorigen;
	}
	
	public String getNuEmi() {
		return nuEmi;
	}
	public void setNuEmi(String nuEmi) {
		this.nuEmi = nuEmi;
	}
	public String getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}

	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getNuCorEmi() {
		return nuCorEmi;
	}
	public void setNuCorEmi(String nuCorEmi) {
		this.nuCorEmi = nuCorEmi;
	}
	public String getCoGru() {
		return coGru;
	}
	public void setCoGru(String coGru) {
		this.coGru = coGru;
	}
	public Date getFecEmision() {
		return fecEmision;
	}
	public void setFecEmision(Date fecEmision) {
		this.fecEmision = fecEmision;
	}
	public String getDeTiEmi() {
		return deTiEmi;
	}
	public void setDeTiEmi(String deTiEmi) {
		this.deTiEmi = deTiEmi;
	}
	

	public String getDesDocumento() {
		return desDocumento;
	}
	public void setDesDocumento(String desDocumento) {
		this.desDocumento = desDocumento;
	}

	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public String getEsDocEmi() {
		return esDocEmi;
	}
	public void setEsDocEmi(String esDocEmi) {
		this.esDocEmi = esDocEmi;
	}
	
	public String getEstadoDocDes() {
		return estadoDocDes;
	}
	public void setEstadoDocDes(String estadoDocDes) {
		this.estadoDocDes = estadoDocDes;
	}
	public String getDependenciaOrigen() {
		return dependenciaOrigen;
	}
	public void setDependenciaOrigen(String dependenciaOrigen) {
		this.dependenciaOrigen = dependenciaOrigen;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCoDepEmi() {
		return coDepEmi;
	}
	public void setCoDepEmi(String coDepEmi) {
		this.coDepEmi = coDepEmi;
	}
	public String getCoEmpEmi() {
		return coEmpEmi;
	}
	public void setCoEmpEmi(String coEmpEmi) {
		this.coEmpEmi = coEmpEmi;
	}
	public String getCoOtrOriEmi() {
		return coOtrOriEmi;
	}
	public void setCoOtrOriEmi(String coOtrOriEmi) {
		this.coOtrOriEmi = coOtrOriEmi;
	}
	public String getNuDocEmi() {
		return nuDocEmi;
	}
	public void setNuDocEmi(String nuDocEmi) {
		this.nuDocEmi = nuDocEmi;
	}
	public String getDeDocSig() {
		return deDocSig;
	}
	public void setDeDocSig(String deDocSig) {
		this.deDocSig = deDocSig;
	}
	public String getTiEmi() {
		return tiEmi;
	}
	public void setTiEmi(String tiEmi) {
		this.tiEmi = tiEmi;
	}
	public String getNuRucEmi() {
		return nuRucEmi;
	}
	public void setNuRucEmi(String nuRucEmi) {
		this.nuRucEmi = nuRucEmi;
	}
	public String getCodTipDoc() {
		return codTipDoc;
	}
	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}


	
	
	

}
