package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.EnvioExterno;

public class DatosDocumentoSgdBean {
	private String  remitente;
	private String origen;
	private String tipoBandeja;
	private String nuExpediente;
	private String numDocumento;

	private String tipoDocumento;
	private String asunto;
	private String motivo;
	private String estado;
	private String fecEmision;
	

	private String feRecDoc;
	private String  existeAnexo; 
	private String numEmi;
	private String anio;
	private List<DestinatarioBean>  lstDestinatario;
	private String coGru;
	private String dependenciaOrigen;
	private String nuDniCiudadano;
	private String nombreCiudadano;
	private String nuDniRepLegal;
	private String nombreRepLegal;
	private String tiEnvMsj;
	private String  tipoEnvioExterno;
	private String codTipDoc;

	


	public String getCodTipDoc() {
		return codTipDoc;
	}
	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}
	public String getTiEnvMsj() {
		return tiEnvMsj;
	}
	public void setTiEnvMsj(String tiEnvMsj) {
		this.tiEnvMsj = tiEnvMsj;
	}

	public String getTipoEnvioExterno() {
		return tipoEnvioExterno;
	}
	public void setTipoEnvioExterno(String tipoEnvioExterno) {
		this.tipoEnvioExterno = tipoEnvioExterno;
	}
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
	public String getFecEmision() {
		return fecEmision;
	}

	public void setFecEmision(String fecEmision) {
		this.fecEmision = fecEmision;
	}

	public String getCoGru() {
		return coGru;
	}

	public void setCoGru(String coGru) {
		this.coGru = coGru;
	}

	public String getDependenciaOrigen() {
		return dependenciaOrigen;
	}

	public void setDependenciaOrigen(String dependenciaOrigen) {
		this.dependenciaOrigen = dependenciaOrigen;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getTipoBandeja() {
		return tipoBandeja;
	}

	public void setTipoBandeja(String tipoBandeja) {
		this.tipoBandeja = tipoBandeja;
	}

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

	public String getFeRecDoc() {
		return feRecDoc;
	}

	public void setFeRecDoc(String feRecDoc) {
		this.feRecDoc = feRecDoc;
	}

	public String getExisteAnexo() {
		return existeAnexo;
	}

	public void setExisteAnexo(String existeAnexo) {
		this.existeAnexo = existeAnexo;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public List<DestinatarioBean> getLstDestinatario() {
		return lstDestinatario;
	}

	public void setLstDestinatario(List<DestinatarioBean> lstDestinatario) {
		this.lstDestinatario = lstDestinatario;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNumEmi() {
		return numEmi;
	}

	public void setNumEmi(String numEmi) {
		this.numEmi = numEmi;
	}

	@Override
	public String toString() {
		return "DatosDocumentoSgdBean{" +
				"remitente='" + remitente + '\'' +
				", origen='" + origen + '\'' +
				", tipoBandeja='" + tipoBandeja + '\'' +
				", nuExpediente='" + nuExpediente + '\'' +
				", numDocumento='" + numDocumento + '\'' +
				", tipoDocumento='" + tipoDocumento + '\'' +
				", asunto='" + asunto + '\'' +
				", motivo='" + motivo + '\'' +
				", estado='" + estado + '\'' +
				", fecEmision='" + fecEmision + '\'' +
				", feRecDoc='" + feRecDoc + '\'' +
				", existeAnexo='" + existeAnexo + '\'' +
				", numEmi='" + numEmi + '\'' +
				", anio='" + anio + '\'' +
				", lstDestinatario=" + lstDestinatario +
				", coGru='" + coGru + '\'' +
				", dependenciaOrigen='" + dependenciaOrigen + '\'' +
				", nuDniCiudadano='" + nuDniCiudadano + '\'' +
				", nombreCiudadano='" + nombreCiudadano + '\'' +
				", nuDniRepLegal='" + nuDniRepLegal + '\'' +
				", nombreRepLegal='" + nombreRepLegal + '\'' +
				", tiEnvMsj='" + tiEnvMsj + '\'' +
				", tipoEnvioExterno='" + tipoEnvioExterno + '\'' +
				", codTipDoc='" + codTipDoc + '\'' +
				'}';
	}
}
