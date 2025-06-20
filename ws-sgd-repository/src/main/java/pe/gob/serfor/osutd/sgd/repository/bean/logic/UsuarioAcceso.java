package pe.gob.serfor.osutd.sgd.repository.bean.logic;

public class UsuarioAcceso {
	private String codigo;
	private String codUser;
	
	private String nombres;
	private String coEmpleado;
	
	private String CoCargo;
	private String deCargo;
	private String esAdmin;
	
	public String getEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(String esAdmin) {
		this.esAdmin = esAdmin;
	}
	public String getCoCargo() {
		return CoCargo;
	}
	public void setCoCargo(String coCargo) {
		CoCargo = coCargo;
	}
	public String getDeCargo() {
		return deCargo;
	}
	public void setDeCargo(String deCargo) {
		this.deCargo = deCargo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodUser() {
		return codUser;
	}
	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getCoEmpleado() {
		return coEmpleado;
	}
	public void setCoEmpleado(String coEmpleado) {
		this.coEmpleado = coEmpleado;
	}
	


}
