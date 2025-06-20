package pe.gob.serfor.osutd.sgd.repository.bean.integracion.response;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.TipoDocumento;


public class TipoDocumentoMP {
	private Mensaje mensaje;
	private List<TipoDocumento>  lstDocumentos;
	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	

	public List<TipoDocumento> getLstDocumentos() {
		return lstDocumentos;
	}

	public void setLstDocumentos(List<TipoDocumento> lstDocumentos) {
		this.lstDocumentos = lstDocumentos;
	}

	

}
