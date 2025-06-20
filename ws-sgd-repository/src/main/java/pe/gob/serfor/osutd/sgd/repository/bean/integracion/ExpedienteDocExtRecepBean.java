package pe.gob.serfor.osutd.sgd.repository.bean.integracion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpedienteDocExtRecepBean {
	private String usCreaAudi;
	private String coDepEmi;
	private String feExp;
	private String feVence;
	private String nuSecExp;
	private String nuAnnExp;
	private String coProceso;
	private String deDetalle;
	private String nuCorrExp;
	private String coTipoExp;

	private String nuExpediente;

	public String getUsCreaAudi() {
		return usCreaAudi;
	}

	public void setUsCreaAudi(String usCreaAudi) {
		this.usCreaAudi = usCreaAudi;
	}

	public String getCoDepEmi() {
		return coDepEmi;
	}

	public void setCoDepEmi(String coDepEmi) {
		this.coDepEmi = coDepEmi;
	}

	public String getFeExp() {
		return feExp;
	}

	public void setFeExp(String feExp) {
		this.feExp = feExp;
	}

	public String getFeVence() {
		return feVence;
	}

	public void setFeVence(String feVence) {
		this.feVence = feVence;
	}

	public String getNuSecExp() {
		return nuSecExp;
	}

	public void setNuSecExp(String nuSecExp) {
		this.nuSecExp = nuSecExp;
	}

	public String getNuAnnExp() {
		return nuAnnExp;
	}

	public void setNuAnnExp(String nuAnnExp) {
		this.nuAnnExp = nuAnnExp;
	}

	public String getCoProceso() {
		return coProceso;
	}

	public void setCoProceso(String coProceso) {
		this.coProceso = coProceso;
	}

	public String getDeDetalle() {
		return deDetalle;
	}

	public void setDeDetalle(String deDetalle) {
		this.deDetalle = deDetalle;
	}

	public String getNuCorrExp() {
		return nuCorrExp;
	}

	public void setNuCorrExp(String nuCorrExp) {
		this.nuCorrExp = nuCorrExp;
	}

	public String getCoTipoExp() {
		return coTipoExp;
	}

	public void setCoTipoExp(String coTipoExp) {
		this.coTipoExp = coTipoExp;
	}

	public String getNuExpediente() {
		return nuExpediente;
	}

	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}

}
