package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import org.codehaus.jackson.annotate.JsonIgnore;

public class Trabajador {
	
	@JsonIgnore
    private String nombres;
    @JsonIgnore
    private String apePaterno;
    @JsonIgnore
    private String apeMaterno;
    private String nombreCompleto;
    private String docIdentidad;
    private String cargo;
    private String tipoDocIdentidad;
    private Integer codTipoDocIdentidad;
    
    public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
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
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDocIdentidad() {
		return docIdentidad;
	}
	public void setDocIdentidad(String docIdentidad) {
		this.docIdentidad = docIdentidad;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}
	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}
	public Integer getCodTipoDocIdentidad() {
		return codTipoDocIdentidad;
	}
	public void setCodTipoDocIdentidad(Integer codTipoDocIdentidad) {
		this.codTipoDocIdentidad = codTipoDocIdentidad;
	}
    
  
    
}
