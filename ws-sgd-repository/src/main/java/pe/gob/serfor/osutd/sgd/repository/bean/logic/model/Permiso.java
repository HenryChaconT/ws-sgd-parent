package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

public class Permiso {
	private String codUser;
	private String cempCodEmp;
	private String codOpc; 
	private String codAplica;
	//P.COD_USER,P.COD_APLICA,P.COD_OPC
	
	public String getCodUser() {
		return codUser;
	}
	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}
	public String getCempCodEmp() {
		return cempCodEmp;
	}
	public void setCempCodEmp(String cempCodEmp) {
		this.cempCodEmp = cempCodEmp;
	}
	public String getCodOpc() {
		return codOpc;
	}
	public void setCodOpc(String codOpc) {
		this.codOpc = codOpc;
	}
	public String getCodAplica() {
		return codAplica;
	}
	public void setCodAplica(String codAplica) {
		this.codAplica = codAplica;
	}

}
