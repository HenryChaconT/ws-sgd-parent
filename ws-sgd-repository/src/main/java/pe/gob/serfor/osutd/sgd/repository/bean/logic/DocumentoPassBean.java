package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DatosCut;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoPass;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class DocumentoPassBean {
	
	private Mensaje  mensaje;
	private DatosCut  datosCut;
	private List<DocumentoPass>  documentos;
	public List<DocumentoPass> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DocumentoPass> documentos) {
		this.documentos = documentos;
	}
	public DatosCut getDatosCut() {
		return datosCut;
	}
	public void setDatosCut(DatosCut datosCut) {
		this.datosCut = datosCut;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

}
