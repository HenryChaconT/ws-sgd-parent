package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class DocumentosMPBean {
	

	private Mensaje  mensaje;

	
	private List<DatosDocumentoSgdMPBean>  documentos;
	
	

	

	public List<DatosDocumentoSgdMPBean> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DatosDocumentoSgdMPBean> documentos) {
		this.documentos = documentos;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}


	@Override
	public String toString() {
		return "DocumentosMPBean{" +
				"mensaje=" + mensaje +
				", documentos=" + documentos +
				'}';
	}
}
