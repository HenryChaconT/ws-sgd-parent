package pe.gob.serfor.osutd.sgd.repository.bean.integracion;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ciudadano;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class CiudadanoBean {
	
	private Ciudadano ciudadano;
	private Mensaje mensaje;
	
	public Ciudadano getCiudadano() {
		return ciudadano;
	}
	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}
	
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	

	
}
