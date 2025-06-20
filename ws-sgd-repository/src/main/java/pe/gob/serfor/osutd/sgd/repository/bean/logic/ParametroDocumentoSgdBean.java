package pe.gob.serfor.osutd.sgd.repository.bean.logic;

public class ParametroDocumentoSgdBean {
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	private String anio;
	private String numExpediente;
	private String numDocumento;
	private String codOficina;
	private String numEmi;
	private String tipDoc;
	private String tipoBusqueda;
	
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	public String getTipDoc() {
		return tipDoc;
	}
	public void setTipDoc(String tipDoc) {
		this.tipDoc = tipDoc;
	}
	

	public String getNumEmi() {
		return numEmi;
	}
	public void setNumEmi(String numEmi) {
		this.numEmi = numEmi;
	}
	public String getCodOficina() {
		return codOficina;
	}
	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
	}

	public String getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}

	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

}
