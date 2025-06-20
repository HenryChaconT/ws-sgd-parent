package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

import java.util.Date;

public class DocumentoInterno {
	
//	a.NU_EMI, a.NU_ANN anio,NU_COR_EMI, a.CO_GRU, ");
//	cadena.append("  a.CO_TIP_DOC_ADM as codTipDoc,IDOSGD.PK_SGD_DESCRIPCION_DE_DOCUMENTO (a.CO_TIP_DOC_ADM) desDocumento,  R.NU_DOC ");
//	cadena.append(" ,ISNULL(R.NU_EXPEDIENTE,'') nu_expediente, IDOSGD.PK_SGD_DESCRIPCION_TI_EMI_EMP (a.NU_ANN, a.NU_EMI) dependenciaDestino, ");
//	cadena.append(" CONVERT(VARCHAR(10),A.FE_EMI,103)+''+   CONVERT(VARCHAR(12),A.FE_EMI,108) fecEmision, ");
//	cadena.append(" DE_ASU,CO_LOC_EMI,  CO_DEP_EMI,  A.CO_EMP_EMI, IDOSGD.PK_SGD_DESCRIPCION_TI_EMI_EMP (a.NU_ANN, a.NU_EMI) remitente"
			
	private String  remitente;
	private String nuExpediente;
	private String coGru;
	private String nuDocumento;
	private String tipoDocumento;
	private String asunto;
	private String motivo;
	private String estado;
	private Date feEmi;
	private Date feRecDoc;
	private String fechaLimite;
	private String coDepEmi;
	private String nuEmi;
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getNuExpediente() {
		return nuExpediente;
	}
	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}
	public String getCoGru() {
		return coGru;
	}
	public void setCoGru(String coGru) {
		this.coGru = coGru;
	}
	public String getNuDocumento() {
		return nuDocumento;
	}
	public void setNuDocumento(String nuDocumento) {
		this.nuDocumento = nuDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFeEmi() {
		return feEmi;
	}
	public void setFeEmi(Date feEmi) {
		this.feEmi = feEmi;
	}
	public Date getFeRecDoc() {
		return feRecDoc;
	}
	public void setFeRecDoc(Date feRecDoc) {
		this.feRecDoc = feRecDoc;
	}
	public String getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public String getCoDepEmi() {
		return coDepEmi;
	}
	public void setCoDepEmi(String coDepEmi) {
		this.coDepEmi = coDepEmi;
	}
	public String getNuEmi() {
		return nuEmi;
	}
	public void setNuEmi(String nuEmi) {
		this.nuEmi = nuEmi;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	private String anio;

}
