package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

import java.util.Date;

public class UsuarioSgd {
	 private String nuDni;
	  private String coUsuario;
	  private String dePrenombres;
	  private String deApellidoPaterno;
	  private String deApellidoMaterno;
	  private String cempCodemp;
	  private Integer tiIdentificacion;
	  private String dePassword;
	  private String dePasswordNuevo;
	  private String InAD;
	 
	public String getInAD() {
		return InAD;
	}
	public void setInAD(String inAD) {
		InAD = inAD;
	}
	public String getNuDni() {
		return nuDni;
	}
	public void setNuDni(String nuDni) {
		this.nuDni = nuDni;
	}
	public String getCoUsuario() {
		return coUsuario;
	}
	public void setCoUsuario(String coUsuario) {
		this.coUsuario = coUsuario;
	}
	public String getDePrenombres() {
		return dePrenombres;
	}
	public void setDePrenombres(String dePrenombres) {
		this.dePrenombres = dePrenombres;
	}
	public String getDeApellidoPaterno() {
		return deApellidoPaterno;
	}
	public void setDeApellidoPaterno(String deApellidoPaterno) {
		this.deApellidoPaterno = deApellidoPaterno;
	}
	public String getDeApellidoMaterno() {
		return deApellidoMaterno;
	}
	public void setDeApellidoMaterno(String deApellidoMaterno) {
		this.deApellidoMaterno = deApellidoMaterno;
	}
	public String getCempCodemp() {
		return cempCodemp;
	}
	public void setCempCodemp(String cempCodemp) {
		this.cempCodemp = cempCodemp;
	}
	public Integer getTiIdentificacion() {
		return tiIdentificacion;
	}
	public void setTiIdentificacion(Integer tiIdentificacion) {
		this.tiIdentificacion = tiIdentificacion;
	}
	public String getDePassword() {
		return dePassword;
	}
	public void setDePassword(String dePassword) {
		this.dePassword = dePassword;
	}
	public String getDePasswordNuevo() {
		return dePasswordNuevo;
	}
	public void setDePasswordNuevo(String dePasswordNuevo) {
		this.dePasswordNuevo = dePasswordNuevo;
	}
	public String getCoDep() {
		return coDep;
	}
	public void setCoDep(String coDep) {
		this.coDep = coDep;
	}
	public String getCoLocal() {
		return coLocal;
	}
	public void setCoLocal(String coLocal) {
		this.coLocal = coLocal;
	}
	public String getIpPC() {
		return ipPC;
	}
	public void setIpPC(String ipPC) {
		this.ipPC = ipPC;
	}
	public String getNombrePC() {
		return nombrePC;
	}
	public void setNombrePC(String nombrePC) {
		this.nombrePC = nombrePC;
	}
	public String getUsuPc() {
		return usuPc;
	}
	public void setUsuPc(String usuPc) {
		this.usuPc = usuPc;
	}
	public String getInAdmin() {
		return inAdmin;
	}
	public void setInAdmin(String inAdmin) {
		this.inAdmin = inAdmin;
	}
	public String getDeDep() {
		return deDep;
	}
	public void setDeDep(String deDep) {
		this.deDep = deDep;
	}
	public String getInClave() {
		return inClave;
	}
	public void setInClave(String inClave) {
		this.inClave = inClave;
	}
	public Date getFeModClave() {
		return feModClave;
	}
	public void setFeModClave(Date feModClave) {
		this.feModClave = feModClave;
	}
	public Date getFeActual() {
		return feActual;
	}
	public void setFeActual(Date feActual) {
		this.feActual = feActual;
	}
	public String getEsUsuario() {
		return esUsuario;
	}
	public void setEsUsuario(String esUsuario) {
		this.esUsuario = esUsuario;
	}
	public int getDiasAntesExpiraClave() {
		return diasAntesExpiraClave;
	}
	public void setDiasAntesExpiraClave(int diasAntesExpiraClave) {
		this.diasAntesExpiraClave = diasAntesExpiraClave;
	}
	public Date getdFecMod() {
		return dFecMod;
	}
	public void setdFecMod(Date dFecMod) {
		this.dFecMod = dFecMod;
	}
	public Date getFullFechaActual() {
		return fullFechaActual;
	}
	public void setFullFechaActual(Date fullFechaActual) {
		this.fullFechaActual = fullFechaActual;
	}
	private String coDep;
	  private String coLocal;
	  private String ipPC;
	  private String nombrePC;
	  private String usuPc;
	 
	  private String inAdmin;
	  private String deDep;
	  private String inClave;//indicador de clave NF:clave no es fuerte, EXP:clave expiro,NE: no expira aun, expiro >0 DIAS DE EXPRIRACION
	  private Date feModClave;
	  private Date feActual;
	  private String esUsuario;
	  private int diasAntesExpiraClave;
	  private Date dFecMod; //fecha de actualizacion del registro
	  private Date fullFechaActual;
	 

}
