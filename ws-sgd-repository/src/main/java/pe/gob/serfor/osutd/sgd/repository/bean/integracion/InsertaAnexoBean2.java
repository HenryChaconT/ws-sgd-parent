package pe.gob.serfor.osutd.sgd.repository.bean.integracion;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo2;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class InsertaAnexoBean2 {
	Anexo2  anexo;
	Mensaje mensaje;
	public Anexo2 getAnexo() {
		return anexo;
	}
	public void setAnexo(Anexo2 anexo) {
		this.anexo = anexo;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

}
