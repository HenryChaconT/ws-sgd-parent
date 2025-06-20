package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;


public class Normativa {
   private String nombre;
   private  String descripcion;
   public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public byte[] getArchivoDigital() {
	return archivoDigital;
}
public void setArchivoDigital(byte[] archivoDigital) {
	this.archivoDigital = archivoDigital;
}
private  byte[] archivoDigital;
	

}
