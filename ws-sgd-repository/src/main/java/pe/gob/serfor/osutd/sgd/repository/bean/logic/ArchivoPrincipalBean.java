package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class ArchivoPrincipalBean {
	private String nomDoc;
	private String byteFile;
	private Mensaje mensaje;
	private String tipoDoc;
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNomDoc() {
		return nomDoc;
	}
	public void setNomDoc(String nomDoc) {
		this.nomDoc = nomDoc;
	}

	
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public String getByteFile() {
		return byteFile;
	}

	public void setByteFile(String byteFile) {
		this.byteFile = byteFile;
	}

}
