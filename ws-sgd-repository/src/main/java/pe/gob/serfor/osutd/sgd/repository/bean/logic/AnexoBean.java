package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class AnexoBean {
	private Integer nuAnn;
	private String nuEmi;

	private Integer nuAne;
	private String byteFile;

	private String nomDoc;

	public String getByteFile() {
		return byteFile;
	}

	public void setByteFile(String byteFile) {
		this.byteFile = byteFile;
	}

	public String getNomDoc() {
		return nomDoc;
	}

	public void setNomDoc(String nomDoc) {
		this.nomDoc = nomDoc;
	}

	private String tipoDoc;

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	private Mensaje mensaje;

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public String getNuEmi() {
		return nuEmi;
	}

	public void setNuEmi(String nuEmi) {
		this.nuEmi = nuEmi;
	}

	public Integer getNuAne() {
		return nuAne;
	}

	public void setNuAne(Integer nuAne) {
		this.nuAne = nuAne;
	}

	public Integer getNuAnn() {
		return nuAnn;
	}

	public void setNuAnn(Integer nuAnn) {
		this.nuAnn = nuAnn;
	}

}
