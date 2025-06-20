package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

public class Ciudadano {

	private String nuDni; // NUMERO DE DNI
	private String ubDep; // UBIGEO DE DOMICILIO DEPARTAMENTO
	private String ubProv; // UBIGEO DE DOMICILIO PROVINCIA
	private String ubDis; // UBIGEO DE DOMICILIO DISTRITO
	private String apePaterno; // APELLIDO PATERNO
	private String apeMaterno; // APELLIDO MATERNO
	private String nombre; // NOMBRES

	private String domicilio; // Dirección
	private String telefono; // Telefono
	private String email; // Email
	private boolean flagActulaizar;
	public String getNuDni() {
		return nuDni;
	}
	public void setNuDni(String nuDni) {
		this.nuDni = nuDni;
	}
	public String getUbDep() {
		return ubDep;
	}
	public void setUbDep(String ubDep) {
		this.ubDep = ubDep;
	}
	public String getUbProv() {
		return ubProv;
	}
	public void setUbProv(String ubProv) {
		this.ubProv = ubProv;
	}
	public String getUbDis() {
		return ubDis;
	}
	public void setUbDis(String ubDis) {
		this.ubDis = ubDis;
	}
	public String getApePaterno() {
		return apePaterno;
	}
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}
	public String getApeMaterno() {
		return apeMaterno;
	}
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isFlagActulaizar() {
		return flagActulaizar;
	}
	public void setFlagActulaizar(boolean flagActulaizar) {
		this.flagActulaizar = flagActulaizar;
	}
	
}
