package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Documento;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class DocumentoBean {
	
	private Mensaje  mensaje;
	
	private List<Documento>  documentos;
	public List<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	

}
