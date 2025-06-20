package pe.gob.serfor.osutd.sgd.repository.bean.logic;

public class DocumentoNormativoBean {
	private Integer idUnidadOrganica;
	private  String descripcion;
	private  String nombre;
	//private  byte[] archivoDigital;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//	public byte[] getArchivoDigital() {
//		return archivoDigital;
//	}
//	public void setArchivoDigital(byte[] archivoDigital) {
//		this.archivoDigital = archivoDigital;
//	}
	
	public Integer getIdUnidadOrganica() {
		return idUnidadOrganica;
	}
	public void setIdUnidadOrganica(Integer idUnidadOrganica) {
		this.idUnidadOrganica = idUnidadOrganica;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
