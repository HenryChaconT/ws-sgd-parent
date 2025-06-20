package pe.gob.serfor.osutd.sgd.repository.bean.ubigeo;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.UsuarioAcceso;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class UsuarioResponse {
	private Mensaje mensaje;
	private UsuarioAcceso usuario;
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public UsuarioAcceso getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioAcceso usuario) {
		this.usuario = usuario;
	}

}
