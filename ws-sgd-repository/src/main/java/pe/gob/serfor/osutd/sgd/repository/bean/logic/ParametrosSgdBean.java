package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.io.Serializable;

public class ParametrosSgdBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String anio;
	private String numExpediente;
	private String numDocumento;
	private String codOficina;
	private String numEmi;
	private String tipDoc;
	private String tipoBusqueda;
	private String esDocOrigen;
	
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getEsDocOrigen() {
		return esDocOrigen;
	}
	public void setEsDocOrigen(String esDocOrigen) {
		this.esDocOrigen = esDocOrigen;
	}
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

	@Override
	public String toString() {
		return "ParametrosSgdBean{" +
				"anio='" + anio + '\'' +
				", numExpediente='" + numExpediente + '\'' +
				", numDocumento='" + numDocumento + '\'' +
				", codOficina='" + codOficina + '\'' +
				", numEmi='" + numEmi + '\'' +
				", tipDoc='" + tipDoc + '\'' +
				", tipoBusqueda='" + tipoBusqueda + '\'' +
				", esDocOrigen='" + esDocOrigen + '\'' +
				'}';
	}
}
