package pe.gob.serfor.osutd.sgd.repository.bean.logic;

public class ParametrosMPBean {
	private String anio;
	private String numExpediente;
	private String numDocumento;
	private String tipoBusqueda;
	private String tipDoc;
	private String numDocIden;
	private String tipEmisor;
	private String numEmi;
	public String getNumEmi() {
		return numEmi;
	}
	public void setNumEmi(String numEmi) {
		this.numEmi = numEmi;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
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
	public String getNumDocIden() {
		return numDocIden;
	}
	public void setNumDocIden(String numDocIden) {
		this.numDocIden = numDocIden;
	}
	public String getTipEmisor() {
		return tipEmisor;
	}
	public void setTipEmisor(String tipEmisor) {
		this.tipEmisor = tipEmisor;
	}

	@Override
	public String toString() {
		return "ParametrosMPBean{" +
				"anio='" + anio + '\'' +
				", numExpediente='" + numExpediente + '\'' +
				", numDocumento='" + numDocumento + '\'' +
				", tipoBusqueda='" + tipoBusqueda + '\'' +
				", tipDoc='" + tipDoc + '\'' +
				", numDocIden='" + numDocIden + '\'' +
				", tipEmisor='" + tipEmisor + '\'' +
				", numEmi='" + numEmi + '\'' +
				'}';
	}
}
