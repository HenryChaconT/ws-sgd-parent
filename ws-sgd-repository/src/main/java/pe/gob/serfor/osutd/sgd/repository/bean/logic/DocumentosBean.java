package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class DocumentosBean {
	

	private Mensaje  mensaje;

	
	private List<DatosDocumentoSgdBean>  documentos;
	private CabeceraDocumentoBean cabecera;


	public CabeceraDocumentoBean getCabecera() {
		return cabecera;
	}
	public void setCabecera(CabeceraDocumentoBean cabecera) {
		this.cabecera = cabecera;
	}
	

	
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public List<DatosDocumentoSgdBean> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DatosDocumentoSgdBean> documentos) {
		this.documentos = documentos;
	}

	@Override
	public String toString() {
		return "DocumentosBean{" +
				"mensaje=" + mensaje +
				", documentos=" + documentos +
				", cabecera=" + cabecera +
				'}';
	}
}
