package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class DocumentoPrincipalBean {

	private List<DestinatarioBean> lstDestinatario;
	private Mensaje mensaje;
	private DatosDocumentoBean datosDocumento;

	public DatosDocumentoBean getDatosDocumento() {
		return datosDocumento;
	}

	public void setDatosDocumento(DatosDocumentoBean datosDocumento) {
		this.datosDocumento = datosDocumento;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public List<DestinatarioBean> getLstDestinatario() {
		return lstDestinatario;
	}

	public void setLstDestinatario(List<DestinatarioBean> lstDestinatario) {
		this.lstDestinatario = lstDestinatario;
	}

}
