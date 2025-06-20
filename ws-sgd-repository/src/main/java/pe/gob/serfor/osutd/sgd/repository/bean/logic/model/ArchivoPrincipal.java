package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

public class ArchivoPrincipal {
	//bl_doc,DE_RUTA_ORIGEN
	
	private byte[] blDoc;
	private String deRutaOrigen;
	public byte[] getBlDoc() {
		return blDoc;
	}
	public void setBlDoc(byte[] blDoc) {
		this.blDoc = blDoc;
	}
	public String getDeRutaOrigen() {
		return deRutaOrigen;
	}
	public void setDeRutaOrigen(String deRutaOrigen) {
		this.deRutaOrigen = deRutaOrigen;
	}
	
	private Mensaje mensaje;
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

}
